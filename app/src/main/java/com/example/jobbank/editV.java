package com.example.jobbank;

public class editV {

    private String description;
    private String qualification;
    private String department;
    private String yrsOfExp;
    private String ageLimit;
    private String jobType;
    private String closingDate;

  /*  public editV(String description, String qualification, String department, String yrsOfExp, String ageLimit, String jobType, String closingDate) {
        this.description = description;
        this.qualification = qualification;
        this.department = department;
        this.yrsOfExp = yrsOfExp;
        this.ageLimit = ageLimit;
        this.jobType = jobType;
        this.closingDate = closingDate;
    }*/

  public editV(){

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
}
