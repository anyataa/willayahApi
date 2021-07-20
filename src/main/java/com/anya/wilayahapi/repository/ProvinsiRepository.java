package com.anya.wilayahapi.repository;

import java.util.List;

import com.anya.wilayahapi.entity.Provinsi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinsiRepository extends JpaRepository<Provinsi, Integer> {

    public Provinsi findByKode(String kodeProvinsi);

    @Query(value = "SELECT * FROM provinsi_table WHERE is_deleted = 1", nativeQuery = true)
    public List<Provinsi> getActiveProvinsi();

}
