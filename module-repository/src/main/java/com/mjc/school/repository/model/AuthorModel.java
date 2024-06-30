package com.mjc.school.repository.model;


public class AuthorModel {

    private Long id;
    private String name;

    public AuthorModel() {
    }

    public AuthorModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
