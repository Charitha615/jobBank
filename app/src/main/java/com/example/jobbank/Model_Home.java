package com.example.jobbank;

public class Model_Home {

    /*String com_Name,qualifications,img_logo;

    public Model_Home(String com_Name, String com_Address, String qualifications, String img_logo) {
        this.com_Name = com_Name;
        this.com_Address = com_Address;
        this.qualifications = qualifications;
        this.img_logo = img_logo;
    }

    public String getCom_Name() {
        return com_Name;
    }

    public void setCom_Name(String com_Name) {
        this.com_Name = com_Name;
    }

    public String getCom_Address() {
        return com_Address;
    }

    public void setCom_Address(String com_Address) {
        this.com_Address = com_Address;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getImg_logo() {
        return img_logo;
    }

    public void setImg_logo(String img_logo) {
        this.img_logo = img_logo;
    }*/

    /*String username,email,description,ProfileImage;

    public Model_Home(String username, String email, String description, String profileImage) {
        this.username = username;
        this.email = email;
        this.description = description;
        ProfileImage = profileImage;
    }

    Model_Home()
    {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }*/


    String companyName,department,closingDate,description,jobTitle,ageLimit,ProfileImage,qualification,jobType;

    public Model_Home(String companyName, String department, String closingDate, String description, String jobTitle, String ageLimit,String profileImage,String qualification,String jobType) {
        this.companyName = companyName;
        this.department = department;
        this.closingDate = closingDate;
        this.description = description;
        this.jobTitle = jobTitle;
        this.ageLimit = ageLimit;
        this.ProfileImage = profileImage;
        this.qualification = qualification;
        this.jobType = jobType;
    }
    Model_Home()
    {

    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }
}
