package com.example.test_grade.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Education {
    @Id
    @Column(name = "teacherId")
    private Long teacherId;

    @Id
    private Long studentId;

    @Column(name="comment")
    private String comment;

    @Transient
    private String teacherName;

    @Transient
    private String studentName;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Education(Long teacherId, Long studentId, String comment, String teacherName, String studentName) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.comment = comment;
        this.teacherName = teacherName;
        this.studentName = studentName;
    }

    public Education() {
    }
}
