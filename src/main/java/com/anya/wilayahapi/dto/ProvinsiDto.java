package com.anya.wilayahapi.dto;

import com.anya.wilayahapi.service.ProvinsiService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvinsiDto implements ProvinsiService {

    private String kode;
    private String nama;
    private Integer is_deleted;

}
