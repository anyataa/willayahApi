package com.anya.wilayahapi.repository;

import java.util.List;

import com.anya.wilayahapi.entity.Kecamatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Integer> {
    public Kecamatan findByKode(String kodeKecamatan);

    @Query(value = "SELECT * FROM kecamatan_table WHERE is_deleted = 1", nativeQuery = true)
    public List<Kecamatan> getActiveKecamatan();

    @Query(value = "SELECT k.nama, p.kode , k.is_deleted FROM kecamatan_table AS k LEFT JOIN kota_table AS p  ON k.id_kota = p.id_kota HAVING p.kode LIKE ?1 AND k.is_deleted = 1", nativeQuery = true)
    public List<?> getbyKodeKota(String kodeKota);

}
