package com.anya.wilayahapi.dto;

import com.anya.wilayahapi.service.DesaService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DesaDto implements DesaService {
    private String kode;
    private String nama;
    private String kodeKecamatan;
    private Integer is_deleted;
}
