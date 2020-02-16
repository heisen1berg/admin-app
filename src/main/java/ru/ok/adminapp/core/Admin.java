package ru.ok.adminapp.core;

public class Admin {
    private long adminID;

    public Admin(long adminID) {
        this.adminID = adminID;
    }

    public long getAdminID() {
        return adminID;
    }

    public void setAdminID(long adminID) {
        this.adminID = adminID;
    }
}
