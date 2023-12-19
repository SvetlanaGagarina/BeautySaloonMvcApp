package org.top.beautysaloonmvcapp.entity;

import jakarta.persistence.*;

import java.util.Set;

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

    @Lob
    @Column(name="preview_image_f", columnDefinition = "MEDIUMBLOB")
    private String previewImageData;// строка хранит байты изображения

    @OneToMany(mappedBy = "procedure")
    private Set<ProcedureSpecialist> procedureSpecialistSet;

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

    public Set<ProcedureSpecialist> getProcedureSpecialistSet() {
        return procedureSpecialistSet;
    }

    public void setProcedureSpecialistSet(Set<ProcedureSpecialist> procedureSpecialistSet) {
        this.procedureSpecialistSet = procedureSpecialistSet;
    }

    public String getPreviewImageData() {
        return previewImageData;
    }

    public void setPreviewImageData(String previewImageData) {
        this.previewImageData = previewImageData;
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
