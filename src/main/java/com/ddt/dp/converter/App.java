/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.converter;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class App {
    /**
     * Program entry point
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        Converter<UserDto, User> userConverter = new Converter<>(
                userDto -> new User(userDto.getFirstName(), userDto.getLastName(), userDto.isActive(),
                        userDto.getEmail()),
                user -> new UserDto(user.getFirstName(), user.getLastName(), user.isActive(), user.getUserId()));

        UserDto dtoUser = new UserDto("John", "Doe", true, "whatever[at]wherever.com");
        User user = userConverter.convertFromDto(dtoUser);
        System.out.println("Entity converted from DTO:" + user);

        ArrayList<User> users = Lists.newArrayList(new User("Camile", "Tough", false, "124sad"),
                new User("Marti", "Luther", true, "42309fd"), new User("Kate", "Smith", true, "if0243"));
        System.out.println("Domain entities:");
        users.forEach(System.out::println);

        System.out.println("DTO entities converted from domain:");
        List<UserDto> dtoEntities = userConverter.createFromEntities(users);
        dtoEntities.forEach(System.out::println);

    }
}
