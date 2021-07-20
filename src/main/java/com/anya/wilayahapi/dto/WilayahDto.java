package com.anya.wilayahapi.dto;

import com.anya.wilayahapi.entity.Provinsi;
import com.anya.wilayahapi.service.WilayahService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WilayahDto implements WilayahService {
    private String kode;
    private String nama;
    private Integer is_deleted;

    @Override
    public void setKodeDanNama(String Kode, String Nama) {
        // TODO Auto-generated method stub

    }

}
