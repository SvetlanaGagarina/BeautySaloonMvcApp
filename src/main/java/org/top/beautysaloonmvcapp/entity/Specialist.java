package org.top.beautysaloonmvcapp.entity;

import jakarta.persistence.*;

import java.util.Set;

// Specialist описывает сущность "Специалист" - запись таблицы специалистов салона
@Entity
@Table(name = "specialist_t")
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;    // ФИО специалиста

    @Column(name="profile_f", nullable = false)
    private String profile;    // Профиль специалиста

    @Column(name="qualification_f", nullable = false)
    private String qualification; // Квалификация специалиста

    //Связи
    @OneToMany(mappedBy = "specialist")
    private Set<Review> reviewSet;

    // конструкторы
    public Specialist() {
        id = 0;
        name = "";
        profile = "";
        qualification = "";
    }

    // getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Set<Review> getReviewSet() {
        return reviewSet;
    }

    public void setReviewSet(Set<Review> reviewSet) {
        this.reviewSet = reviewSet;
    }

// ToString

    @Override
    public String toString() {
        return "Specialist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profile='" + profile + '\'' +
                ", qualification='" + qualification + '\'' +
                '}';
    }
}
