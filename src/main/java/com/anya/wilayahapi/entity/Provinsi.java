package com.anya.wilayahapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "provinsi_table")
@Entity
@Data
@NoArgsConstructor
public class Provinsi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_provinsi;
    @Column(name = "kode", length = 45, nullable = false)
    private String kode;
    @Column(name = "nama", length = 45, nullable = false)
    private String nama;
    @Column(name = "is_deleted", nullable = false, columnDefinition = "integer default 1")
    private Integer is_deleted;

}
