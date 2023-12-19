package org.top.beautysaloonmvcapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="procedureSpecialist_t")
public class ProcedureSpecialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // поля связей, через которые будет реализована связь many-to-many
    @ManyToOne
    @JoinColumn(name="procedure_id", nullable = false)
    private Procedure procedure;

    @ManyToOne
    @JoinColumn(name="specialist_id", nullable = false)
    private Specialist specialist;

    //
    public ProcedureSpecialist() {
        id = 0;
        specialist = new Specialist();// чтоб всегда тут был объект
        procedure = new Procedure(); // чтоб были не пустые
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }
}
