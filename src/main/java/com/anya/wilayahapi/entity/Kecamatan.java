package com.anya.wilayahapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.ManyToAny;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "kecamatan_table")
@Entity
public class Kecamatan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kecamatan", nullable = false, unique = true)
    private Integer id_kecamatan;
    @Column(name = "nama")
    private String nama;
    @Column(name = "kode")
    private String kode;
    @Column(name = "is_deleted")
    private Integer is_deleted;
    @ManyToOne
    @JoinColumn(name = "id_kota", insertable = true, updatable = true)
    private Kota id_kota;

}
