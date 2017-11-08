/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.datamapper;

import java.io.Serializable;

public final class Student implements Serializable {
    private static final long serialVersionUID = 142596831629821798L;

    private int studentId;

    private String name;

    private char grade;

    public Student(final int studentId, final String name, final char grade) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(final Object inputObj) {
        boolean isEqual = false;

        if (inputObj == null) {
            isEqual = false;
        } else if (getClass() == inputObj.getClass()) {
            final Student student = (Student) inputObj;

            if (this.getStudentId() == student.getStudentId()) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getStudentId();
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", name=" + name + ", grade=" + grade + "]";
    }
}
