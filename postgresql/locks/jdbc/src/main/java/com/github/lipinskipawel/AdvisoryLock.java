package com.github.lipinskipawel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.Objects.requireNonNull;

public final class AdvisoryLock {

    private final DataSource dataSource;
    private final AtomicReference<Connection> lockedConnection;
    private final ReentrantLock connectionLock;

    public AdvisoryLock(DataSource dataSource) {
        this.dataSource = requireNonNull(dataSource);
        this.lockedConnection = new AtomicReference<>(null);
        this.connectionLock = new ReentrantLock();
    }

    public boolean lockOnSession(String key) {
        if (isLocked()) {
            return false;
        }

        try {
            connectionLock.lock();

            final var locked = lockConnection();
            if (!locked) {
                return false;
            }
        } finally {
            connectionLock.unlock();
        }

        try (var stmt = lockedConnection.get().createStatement()) {
            try (var res = stmt.executeQuery("SELECT pg_try_advisory_lock(%d)".formatted(key.hashCode()))) {
                res.next();
                return res.getBoolean(1);
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean unlockOnSession(String key) {
        if (!isLocked()) {
            return false;
        }

        try {
            connectionLock.lock();
            if (!isLocked()) {
                return false;
            }

            try (var conn = lockedConnection.get();
                 var stmt = conn.createStatement()) {
                try (var __ = stmt.executeQuery("SELECT pg_advisory_unlock(%d)".formatted(key.hashCode()))) {
                    lockedConnection.set(null);
                    return true;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } finally {
            connectionLock.unlock();
        }
    }

    private boolean lockConnection() {
        try {
            var connection = dataSource.getConnection();
            if (isLocked()) {
                return false;
            }
            lockedConnection.set(connection);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private boolean isLocked() {
        return lockedConnection.get() != null;
    }
}
