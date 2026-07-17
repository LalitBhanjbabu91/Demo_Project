package com.example.employeeapi.interview;

public class RecordProgram {

    static void main() {
        UserDto userDto = new UserDto("Lalit", 25);
        System.out.println(userDto.name());
        System.out.println(userDto.age());
        System.out.println(userDto);
    }


}
