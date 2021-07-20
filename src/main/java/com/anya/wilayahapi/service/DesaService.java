package com.anya.wilayahapi.service;

import com.anya.wilayahapi.entity.Desa;
import com.anya.wilayahapi.entity.Kecamatan;

public interface DesaService {

    public default void setDelete(Desa desa, boolean status_isDelete) {
        if (status_isDelete) {
            desa.setIs_deleted(0);
        } else {
            desa.setIs_deleted(1);
        }

    }

    public default void setData(Desa desa, String nama, String kode, Kecamatan kecamatan) {
        desa.setKode(kode);
        desa.setNama(nama);
        desa.setId_kecamatan(kecamatan);
        desa.setIs_deleted(1);
    }
}
