/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.datamapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public final class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        /* Create new data mapper for type 'first' */
        final StudentDataMapper mapper = new StudentDataMapperImpl();

        /* Create new student */
        Student student = new Student(1, "Adam", 'A');
        /* Add student in respectibe store */
        mapper.insert(student);
        LOGGER.info("App.main(), student : " + student + ", is inserted");

           /* Find this student */
        final Optional<Student> studentToBeFound = mapper.find(student.getStudentId());

        LOGGER.info("App.main(), student : " + studentToBeFound + ", is searched");

        /* Update existing student object */
        student = new Student(student.getStudentId(), "AdamUpdated", 'A');

        /* Update student in respectibe db */
        mapper.update(student);

        LOGGER.info("App.main(), student : " + student + ", is updated");
        LOGGER.info("App.main(), student : " + student + ", is going to be deleted");

        /* Delete student in db */
        mapper.delete(student);
    }

}
