package com.anya.wilayahapi.service;

import com.anya.wilayahapi.entity.Kecamatan;
import com.anya.wilayahapi.entity.Kota;

public interface KecamatanService {

    public default void setDelete(Kecamatan kecamatan, boolean status_isDelete) {
        if (status_isDelete) {
            kecamatan.setIs_deleted(0);
        } else {
            kecamatan.setIs_deleted(1);
        }

    }

    public default void setData(Kecamatan kecamatan, String nama, String kode, Kota kota) {
        kecamatan.setKode(kode);
        kecamatan.setNama(nama);
        kecamatan.setId_kota(kota);
        kecamatan.setIs_deleted(1);
    }

}
