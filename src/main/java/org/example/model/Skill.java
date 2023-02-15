package org.example.model;

public class Skill {
    private int id;
    private String name;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}
