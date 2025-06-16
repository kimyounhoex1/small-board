package com.jungle.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private long id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "[Member]: id = " + id + ", name = " + name + ", age = " + age;
    }
}
