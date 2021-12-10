package com.example.springdatajdbcdemo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.StringJoiner;

@Table(value = "USERS_PESS")
public class UserPessimistic implements Serializable {

    @Id
    private Long id;

    @Column("NAME")
    private String name;

    @Column("LAST_NAME")
    private String lastname;

    public UserPessimistic() {
    }

    public UserPessimistic(Long id) {
        this.id = id;
    }

    public UserPessimistic(Long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", UserPessimistic.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("lastname='" + lastname + "'")
                .toString();
    }
}
