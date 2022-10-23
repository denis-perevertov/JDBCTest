package entity;

import java.sql.Date;

public class User {

    private int id;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private Date birth_date;
    private String phone_number;
    private String country;
    private String city;
    private String nationality;

    public User() {}

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(int id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(int id, String name, String surname, int age, String gender,
                Date birth_date, String phone_number, String country,
                String city, String nationality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.country = country;
        this.city = city;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    public String fullInfo() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age + '\'' +
                ", gender=" + gender + '\'' +
                ", birth_date=" + birth_date + '\'' +
                ", phone_number=" + phone_number + '\'' +
                ", country=" + country + '\'' +
                ", city=" + city + '\'' +
                ", nationality=" + nationality +
                '}';
    }
}
