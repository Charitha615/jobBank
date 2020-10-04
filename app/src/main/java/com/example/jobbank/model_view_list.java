package com.example.jobbank;

public class model_view_list {
    private String full_name,nic_number,email,phone,experience,age,description,qualifications,closingDate,company,position,vacancyStatus,remarks,status;
    private  int marks;

    public model_view_list() {
    }

    public model_view_list(String full_name, String nic_number, String email, String phone, String experience, String age, String description, String qualifications, String closingDate, String company, String position, String vacancyStatus, String remarks, String status, int marks) {
        this.full_name = full_name;
        this.nic_number = nic_number;
        this.email = email;
        this.phone = phone;
        this.experience = experience;
        this.age = age;
        this.description = description;
        this.qualifications = qualifications;
        this.closingDate = closingDate;
        this.company = company;
        this.position = position;
        this.vacancyStatus = vacancyStatus;
        this.remarks = remarks;
        this.status = status;
        this.marks = marks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
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

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVacancyStatus() {
        return vacancyStatus;
    }

    public void setVacancyStatus(String vacancyStatus) {
        this.vacancyStatus = vacancyStatus;
    }
}
