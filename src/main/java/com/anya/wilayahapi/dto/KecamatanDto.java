package com.anya.wilayahapi.dto;

import com.anya.wilayahapi.service.KecamatanService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KecamatanDto implements KecamatanService {
    private String kode;
    private String nama;
    private String kodeKota;
    private Integer is_deleted;
}
