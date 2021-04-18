package com.example.homework2.models;

public class Entertainment {

    public EntertainmentType getType() {
        return type;
    }

    public void setType(EntertainmentType type) {
        this.type = type;
    }

    EntertainmentType type;
    Entertainment(EntertainmentType type) {
        this.type = type;
    }
}
