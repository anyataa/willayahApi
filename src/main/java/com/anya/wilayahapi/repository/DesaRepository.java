package com.anya.wilayahapi.repository;

import java.util.List;

import com.anya.wilayahapi.entity.Desa;
import com.anya.wilayahapi.entity.Kecamatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DesaRepository extends JpaRepository<Desa, Integer> {
    public Desa findByKode(String kodeDesa);

    @Query(value = "SELECT * FROM desa_table WHERE is_deleted = 1", nativeQuery = true)
    public List<Desa> getActiveDesa();

    @Query(value = "SELECT k.nama, p.kode , k.is_deleted FROM desa_table AS k LEFT JOIN kecamatan_table AS p  ON k.id_kecamatan = p.id_kecamatan HAVING p.kode LIKE ?1 AND k.is_deleted = 1", nativeQuery = true)
    public List<?> getbyKodeKecamatan(String kodeKecamatan);

}
