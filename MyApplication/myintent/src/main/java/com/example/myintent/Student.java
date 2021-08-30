package com.example.myintent;

import java.io.Serializable;

public class Student implements Serializable {
    public String name;
    public int id;
    public int age;

    public Student(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }
}
