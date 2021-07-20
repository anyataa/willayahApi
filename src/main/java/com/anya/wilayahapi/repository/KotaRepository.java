package com.anya.wilayahapi.repository;

import java.util.List;

import com.anya.wilayahapi.entity.Kota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KotaRepository extends JpaRepository<Kota, Integer> {
    public Kota findByKode(String kodeKota);

    @Query(value = "SELECT * FROM kota_table WHERE is_deleted = 1", nativeQuery = true)
    public List<Kota> getActiveKota();

    @Query(value = "SELECT k.nama, p.kode , k.is_deleted FROM kota_table AS k LEFT JOIN provinsi_table AS p  ON k.id_provinsi = p.id_provinsi  HAVING p.kode LIKE ?1 AND k.is_deleted = 1", nativeQuery = true)
    public List<?> getByProvinsi(String kodeProvinsi);

}
