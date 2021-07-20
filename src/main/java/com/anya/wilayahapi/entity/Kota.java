package com.anya.wilayahapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "kota_table")
@Entity
@Data
@NoArgsConstructor
public class Kota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kota", nullable = false, unique = true)
    private Integer id_kota;

    @Column(name = "kode", length = 45, nullable = false)
    private String kode;

    @Column(name = "nama", length = 45, nullable = false)
    private String nama;
    @Column(name = "is_deleted", nullable = false, columnDefinition = "integer default 1")
    private Integer is_deleted;
    @ManyToOne
    @JoinColumn(name = "id_provinsi", insertable = true, updatable = true)
    private Provinsi id_provinsi;

}
