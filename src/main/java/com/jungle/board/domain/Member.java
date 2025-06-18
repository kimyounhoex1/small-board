package com.jungle.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private long id;
    private String name;
    private String nickname;
    private String password;
    private int age;

    @Override
    public String toString() {
        return "[Member]: id = " + id +
                ", name = " + name + ", nickname = " + nickname +
                ", password = " + password + ", age = " + age;
    }
}
