package com.springproject.SpringProject.dto;

public class MemberDTO {
    private int id;
    private String username;
    private String email;
    private String role;
    private String address;

    // Constructors
    public MemberDTO() {}

    public MemberDTO(int id, String username, String email, String role, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.address = address;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
