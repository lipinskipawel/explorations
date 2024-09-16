package com.github.lipinskipawel.dbTransactions.active;

import com.github.lipinskipawel.dbTransactions.CommonAbstractRepository;
import com.github.lipinskipawel.dbTransactions.CommonMapper;
import com.github.lipinskipawel.dbTransactions.CommonUser;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public final class User extends CommonAbstractRepository {
    private UUID id;
    private String username;
    private Instant lastVisit;

    public User(DataSource dataSource) {
        super(dataSource);
    }

    public int save() {
        return db.executeInsert(CommonMapper.toRecord(new CommonUser(id, username, lastVisit)));
    }

    public int update() {
        return db.executeUpdate(CommonMapper.toRecord(new CommonUser(id, username, lastVisit)));
    }

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = requireNonNull(id);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = requireNonNull(username);
        return this;
    }

    public Instant getLastVisit() {
        return lastVisit;
    }

    public User setLastVisit(Instant lastVisit) {
        this.lastVisit = requireNonNull(lastVisit);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(lastVisit, user.lastVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, lastVisit);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastVisit=" + lastVisit +
                '}';
    }
}
