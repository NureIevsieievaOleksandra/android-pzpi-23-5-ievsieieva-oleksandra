package ievsieieva.oleksandra.nure.data.model;

public class User {
    Integer id;
    String name;
    Integer age;

    public User(Integer id, String name, Integer age) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getId() {
        return id;
    }
}
