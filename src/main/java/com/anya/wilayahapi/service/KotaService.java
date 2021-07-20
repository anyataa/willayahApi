package com.anya.wilayahapi.service;

import com.anya.wilayahapi.entity.Kota;
import com.anya.wilayahapi.entity.Provinsi;

public interface KotaService {
    public default Integer setDelete() {
        return 0;
        // 0 = true, is delete is true
    };

    public default void setDelete(Kota kota, boolean status) {
        if (status) {
            kota.setIs_deleted(0);
        } else {
            kota.setIs_deleted(1);
        }

    }

    public default void setData(Kota kota, String kode, String nama, Provinsi provinsi) {
        kota.setKode(kode);
        kota.setNama(nama);
        kota.setIs_deleted(1);
        kota.setId_provinsi(provinsi);

    };

}
