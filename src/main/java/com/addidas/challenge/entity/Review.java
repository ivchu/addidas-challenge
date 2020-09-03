package com.addidas.challenge.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Integer rating;
    private Boolean active = true;
    @CreationTimestamp
    private Date date;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
