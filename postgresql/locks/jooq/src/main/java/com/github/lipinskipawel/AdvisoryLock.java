package com.github.lipinskipawel;

import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import static org.jooq.SQLDialect.POSTGRES;

public final class AdvisoryLock {

    private final ConnectionProvider connectionProvider;
    private final AtomicReference<Connection> lockedConnection;
    private final ReentrantLock connectionLock;

    public AdvisoryLock(DataSource dataSource) {
        final var db = DSL.using(dataSource, POSTGRES);
        this.connectionProvider = db.configuration().connectionProvider();
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

        final var result = useConnection(lockedConnection.get())
            .fetch("SELECT pg_try_advisory_lock(%d)".formatted(key.hashCode()));
        if (result.isEmpty()) {
            return false;
        }
        return result
            .getFirst()
            .map(it -> it.getValue(0, Boolean.class));
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

            try {
                final var result = useConnection(lockedConnection.get())
                    .fetch("SELECT pg_advisory_unlock(%d)".formatted(key.hashCode()));
                if (result.isEmpty()) {
                    return false;
                }
                return result
                    .getFirst()
                    .map(it -> it.getValue(0, Boolean.class));
            } finally {
                releaseConnection(lockedConnection.get());
                lockedConnection.set(null);
            }
        } finally {
            connectionLock.unlock();
        }
    }

    void releaseConnection(Connection capturedConnection) {
        connectionProvider.release(capturedConnection);
    }

    private DSLContext useConnection(Connection connection) {
        return DSL.using(connection);
    }

    private boolean lockConnection() {
        if (isLocked()) {
            return false;
        }
        final var connection = connectionProvider.acquire();
        lockedConnection.set(connection);
        return true;
    }

    private boolean isLocked() {
        return lockedConnection.get() != null;
    }
}
