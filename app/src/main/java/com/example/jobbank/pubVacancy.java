package com.example.jobbank;

public class pubVacancy {
    private String jobTitle;
    private String description;
    private String qualification;
    private String department;
    private String yrsOfExp;
    private String ageLimit;
    private String jobType;
    private String closingDate,publishedDate;
    private String publishedID;
    private String CompanyName;
    //private String CompanyAddress;
    private String CompanyLogo;

    public pubVacancy(){

    }

    public pubVacancy(String jobTitle, String description, String qualification, String department, String yrsOfExp, String ageLimit, String jobType, String closingDate, String publishedDate, String CompanyName, String publishedID, String CompanyLogo) {
        this.jobTitle = jobTitle;
        this.description = description;
        this.qualification = qualification;
        this.department = department;
        this.yrsOfExp = yrsOfExp;
        this.ageLimit = ageLimit;
        this.jobType = jobType;
        this.closingDate = closingDate;
        this.publishedDate = publishedDate;
        this.CompanyName = CompanyName;
        this.publishedID = publishedID;
        //this.CompanyAddress = CompanyAddress;
        this.CompanyLogo = CompanyLogo;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYrsOfExp() {
        return yrsOfExp;
    }

    public void setYrsOfExp(String yrsOfExp) {
        this.yrsOfExp = yrsOfExp;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

//    public String getCompanyAddress() {
//        return CompanyAddress;
//    }

//    public void setCompanyAddress(String companyAddress) {
//        CompanyAddress = companyAddress;
//    }

    public String getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        CompanyLogo = companyLogo;
    }

    public String getPublishedID() {
        return publishedID;
    }

    public void setPublishedID(String publishedID) {
        this.publishedID = publishedID;
    }


    //    public String getCompanyID() {
//        return CompanyID;
//    }
//
//    public void setCompanyID(String companyID) {
//        CompanyID = companyID;
//    }
}
