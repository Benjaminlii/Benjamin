package com.thoughtCoding.alarm.model;

public class Contact {
    private Short cId;

    private Short userId;

    private String callPhone;

    public Short getcId() {
        return cId;
    }

    public void setcId(Short cId) {
        this.cId = cId;
    }

    public Short getUserId() {
        return userId;
    }

    public void setUserId(Short userId) {
        this.userId = userId;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone == null ? null : callPhone.trim();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "cId=" + cId +
                ", userId=" + userId +
                ", callPhone='" + callPhone + '\'' +
                '}';
    }
}