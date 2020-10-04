package com.example.jobbank;

public class model_company_bookmarked {
    String full_name, position;

    public model_company_bookmarked() {
    }

    public model_company_bookmarked(String full_name, String position) {
        this.full_name = full_name;
        this.position = position;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
