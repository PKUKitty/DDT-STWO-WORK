/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.converter;

import java.util.Objects;

public class UserDto {

    private String firstName;

    private String lastName;

    private boolean isActive;

    private String email;

    public UserDto(String firstName, String lastName, boolean isActive, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getEmail() {
        return email;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (null == object || getClass() != object.getClass()) {
            return false;
        }

        UserDto userDto = (UserDto) object;
        return isActive == userDto.isActive && Objects.equals(firstName, userDto.firstName) && Objects
                .equals(lastName, userDto.lastName) && Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, isActive, email);
    }

    @Override
    public String toString() {
        return "UserDto{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
                + ", isActive=" + isActive + ", email='" + email + '\'' + '}';
    }
}
