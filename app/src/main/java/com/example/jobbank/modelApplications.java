package com.example.jobbank;

public class modelApplications {

    String position, company, closingDate, status;

    String full_name;
    String nic_number;
    String email;
    String phone;

    String experience;
    String age;
    String description;
    String qualifications;


    public modelApplications() {

    }

    public modelApplications(String position, String company, String closingDate, String status, String full_name, String nic_number, String email, String phone,
                             String experience, String age, String description, String qualifications) {
        this.position = position;
        this.company = company;
        this.closingDate = closingDate;
        this.status = status;
        this.full_name = full_name;
        this.nic_number = nic_number;
        this.email = email;
        this.phone = phone;
        this.experience = experience;
        this.age = age;
        this.description = description;
        this.qualifications = qualifications;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getNic_number() {
        return nic_number;
    }

    public void setNic_number(String nic_number) {
        this.nic_number = nic_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}
