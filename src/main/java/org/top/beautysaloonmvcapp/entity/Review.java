package org.top.beautysaloonmvcapp.entity;

import jakarta.persistence.*;

import java.util.Date;

// Review описывает сущность "Отзыв о специалисте" - запись таблицы отзывов о специалистах
@Entity
@Table(name = "review_t")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="review_rate_f", nullable = false)
    private Double reviewRate;      // оценка специалиста, поставленная автором пользователя

    @Column(name="comment_f")
    private String comment;         // комментарий отзыва

    @Column(name="written_in")
    private Date writtenIn;//дата отзыва, для сортировки отзывов и возможностей редактирования отзыва

    // Связь: отзыв ссылается на специалиста, много отзывов к одному специалисту
    @ManyToOne
    @JoinColumn(name="specialist_id", nullable = false)
    private Specialist specialist;

    // конструктор
    public Review(){
        id = 0;
        reviewRate = 0.0;
        comment = null;
    }

    // гетеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(Double reviewRate) {
        this.reviewRate = reviewRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getWrittenIn() {
        return writtenIn;
    }

    public void setWrittenIn(Date writtenIn) {
        this.writtenIn = writtenIn;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    // toString()
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewRate=" + reviewRate +
                ", comment='" + comment + '\'' +
                ", writtenIn=" + writtenIn +
                '}';
    }
}
