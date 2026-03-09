package com.jungle.board.messages;

public class Greeting {
    private String content;

    public Greeting(){}
    public Greeting(String content) {
        this.content = content;
    }
    public String getName() {
        return this.content;
    }
    public void setName(String content) {
        this.content = content;
    }
}
