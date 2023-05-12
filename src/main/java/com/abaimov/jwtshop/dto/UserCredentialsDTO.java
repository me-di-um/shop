package com.abaimov.jwtshop.dto;

public class UserCredentialsDTO {
    private String firstname;
    private String lastname;

    private String newPassword;

    public UserCredentialsDTO(String firstname, String lastname, String newPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
