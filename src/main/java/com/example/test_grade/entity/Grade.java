package com.example.test_grade.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Grade {
    @Id
    private Long studentId;

    @Id
    private Long courseId;

    @Column(name="type")
    private String type;

    @Column(name="regularscore")
    private Double regularscore;
    @Column(name="volumescore")
    private Double volumescore;
    @Column(name="score")
    private Double score;
    @Column(name="gradepoint")
    private String gradepoint;

    @Transient
    private String studentName;

    @Transient
    private String courseName;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getRegularscore() {
        return regularscore;
    }

    public void setRegularscore(Double regularscore) {
        this.regularscore = regularscore;
    }

    public Double getVolumescore() {
        return volumescore;
    }

    public void setVolumescore(Double volumescore) {
        this.volumescore = volumescore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getGradepoint() {
        return gradepoint;
    }

    public void setGradepoint(String gradepoint) {
        this.gradepoint = gradepoint;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Grade(Long studentId, Long courseId, String type, Double regularscore, Double volumescore, Double score, String gradepoint, String studentName, String courseName) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.type = type;
        this.regularscore = regularscore;
        this.volumescore = volumescore;
        this.score = score;
        this.gradepoint = gradepoint;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public Grade() {
    }
}
