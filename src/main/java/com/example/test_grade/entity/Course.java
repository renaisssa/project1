package com.example.test_grade.entity;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="credit")
    private String credit;
    @Column(name="time")
    private String time;
    @Column(name="place")
    private String place;
    @Column(name="period")
    private String period;
    @Column(name="classno")
    private String classno;
    @Column(name="teacherId")
    private String teacherId;

    @Transient
    private String teacherName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Course(Long id, String name, String credit, String time, String place, String period, String classno, String teacherId, String teacherName) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.time = time;
        this.place = place;
        this.period = period;
        this.classno = classno;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public Course() {
    }
}
