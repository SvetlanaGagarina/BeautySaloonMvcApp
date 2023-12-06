package org.top.beautysaloonmvcapp.entity;

import jakarta.persistence.*;

// Procedure описывает сущность "Процедура" - запись таблицы услуг поштучно
@Entity
@Table(name = "procedure_t")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;    // наименование услуги

    @Column(name="price_f", nullable = false)
    private Integer price;    // стоимость услуги в одном экземпляре

    // конструкторы
    public Procedure() {
        id = 0;
        name = "";
        price = 0;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    // ToString


    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
