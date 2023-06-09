package org.example;

public class Person {
    private int id;
    private String firstName;
    private int age;

    private String email;
    public Person() {
    }

    public Person(int id, String firstName, int age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
