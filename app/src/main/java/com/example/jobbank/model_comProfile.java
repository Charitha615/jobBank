package com.example.jobbank;

public class model_comProfile {
    String comName, comVision,comMission,comEmp,comDept,comAddress,comEmail,comPhone;

    public model_comProfile() {
    }

    public model_comProfile(String comName, String comVision, String comMission, String comEmp, String comDept, String comAddress, String comEmail, String comPhone) {
        this.comName = comName;
        this.comVision = comVision;
        this.comMission = comMission;
        this.comEmp = comEmp;
        this.comDept = comDept;
        this.comAddress = comAddress;
        this.comEmail = comEmail;
        this.comPhone = comPhone;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComVision() {
        return comVision;
    }

    public void setComVision(String comVision) {
        this.comVision = comVision;
    }

    public String getComMission() {
        return comMission;
    }

    public void setComMission(String comMission) {
        this.comMission = comMission;
    }

    public String getComEmp() {
        return comEmp;
    }

    public void setComEmp(String comEmp) {
        this.comEmp = comEmp;
    }

    public String getComDept() {
        return comDept;
    }

    public void setComDept(String comDept) {
        this.comDept = comDept;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getComEmail() {
        return comEmail;
    }

    public void setComEmail(String comEmail) {
        this.comEmail = comEmail;
    }

    public String getComPhone() {
        return comPhone;
    }

    public void setComPhone(String comPhone) {
        this.comPhone = comPhone;
    }
}
