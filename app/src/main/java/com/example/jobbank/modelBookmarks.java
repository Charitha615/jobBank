package com.example.jobbank;

public class modelBookmarks {
    String userID;
    String companyName;

    public modelBookmarks() {
    }

    public modelBookmarks(String userID, String companyName) {
        this.userID = userID;
        this.companyName = companyName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
