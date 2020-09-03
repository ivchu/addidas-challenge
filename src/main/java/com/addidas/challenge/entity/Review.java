package com.addidas.challenge.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String text;
    private Integer rating;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
