package com.codyneeraj.pegasus;

public class UserInfoHolder {
   private String name, contact, course, avatar;

    public UserInfoHolder(String name, String contact, String course, String avatar) {
        this.name = name;
        this.contact = contact;
        this.course = course;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
