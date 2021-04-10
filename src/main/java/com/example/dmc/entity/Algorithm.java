package com.example.dmc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "algorithms")
public class Algorithm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "file_name")
    private String fileName;

    @OneToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "input_type")
    private Type inputType;

    @Enumerated(EnumType.STRING)
    @Column(name = "output_type")
    private Type outputType;
}
