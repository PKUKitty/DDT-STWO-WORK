/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.datamapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class StudentDataMapperImpl implements StudentDataMapper {

    /* Note: Normally this would be in the form of an actual database */
    private List<Student> students = new ArrayList<>();

    @Override
    public Optional<Student> find(int studentId) {
        for (final Student student : students) {
            if (student.getStudentId() == studentId) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    @Override
    public void insert(Student studentToBeInserted) throws DataMapperException {
         /* Check with existing students */
        if (!this.getStudents().contains(studentToBeInserted)) {
             /* Add student in list */
            this.getStudents().add(studentToBeInserted);
        } else {
            /* Throw user error after wrapping in a runtime exception */
            throw new DataMapperException("Student already [" + studentToBeInserted.getName() + "] exists");
        }
    }

    @Override
    public void update(Student studentToBeUpdated) throws DataMapperException {
        /* Check with existing students */
        if (this.getStudents().contains(studentToBeUpdated)) {
            /* Get the index of student in list */
            final int index = this.getStudents().indexOf(studentToBeUpdated);
            /* Update the student in list */
            this.getStudents().set(index, studentToBeUpdated);
        } else {
            /* Throw user error after wrapping in a runtime exception */
            throw new DataMapperException("Student [" + studentToBeUpdated.getName() + "] is not found");
        }
    }

    @Override
    public void delete(Student studentToBeDeleted) throws DataMapperException {
         /* Check with existing students */
        if (this.getStudents().contains(studentToBeDeleted)) {
            /* Delete the student from list */
            this.getStudents().remove(studentToBeDeleted);
        } else {
            /* Throw user error after wrapping in a runtime exception */
            throw new DataMapperException("Student [" + studentToBeDeleted.getName() + "] is not found");
        }
    }

    public List<Student> getStudents() {
        return this.students;
    }

}
