package com.example.dmc.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "data_sets")
public class DataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "file_name")
    private String fileName;
}
