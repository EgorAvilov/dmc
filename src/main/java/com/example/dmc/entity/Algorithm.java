package com.example.dmc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "algorithms")
public class Algorithm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Task> tasks = new ArrayList<>();

    @Column(name = "name")
    private String name;
}
