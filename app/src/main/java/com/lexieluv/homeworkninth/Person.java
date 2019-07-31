package com.lexieluv.homeworkninth;


public class Person {
    private String name;
    private String gender;
    private String time;
    Book book;

    public Person(){ }

    public Person(String name, String gender, String time, Book book) {
        this.name = name;
        this.gender = gender;
        this.time = time;
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + getName() + '\'' +
                ", gender='" + getGender() + '\'' +
                ", time='" + getTime() + '\'' +
                ", book=" + getBook() +
                '}';
    }
}
