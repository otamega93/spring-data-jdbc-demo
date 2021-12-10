package com.example.springdatajdbcdemo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.StringJoiner;

@Table(value = "USERS_OPT")
public class UserOptimistic implements Serializable {

    @Id
    private Long id;

    @Column("NAME")
    private String name;

    @Column("LAST_NAME")
    private String lastname;

    @Column("VERSION")
    @Version
    private Long version;

    public UserOptimistic() {
    }

    public UserOptimistic(Long id) {
        this.id = id;
    }

    public UserOptimistic(Long id, String name, String lastname, Long version) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getVersion() {
        return version;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserOptimistic.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("lastname='" + lastname + "'")
                .add("version=" + version)
                .toString();
    }
}
