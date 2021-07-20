package com.anya.wilayahapi.dto;

import com.anya.wilayahapi.service.KotaService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KotaDto implements KotaService {
    private String kode;
    private String nama;
    private String kodeProvinsi;
    private Integer is_deleted;
    // private Integer id_provinsi;

}
