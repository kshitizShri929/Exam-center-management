package com.example;

import jakarta.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double score;

    @ManyToOne(fetch = FetchType.LAZY) // Use FetchType.LAZY for better performance
    @JoinColumn(name = "exam_center_id", nullable = false) // Maps the foreign key column
    private ExamCenter examCenter;

    // Default constructor
    public Student() {
    }

    // Constructor with fields
    public Student(String name, Double score, ExamCenter examCenter) {
        this.name = name;
        this.score = score;
        this.examCenter = examCenter;
    }

    // Getters and setters
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ExamCenter getExamCenter() {
        return examCenter;
    }

    public void setExamCenter(ExamCenter examCenter) {
        this.examCenter = examCenter;
    }
}
