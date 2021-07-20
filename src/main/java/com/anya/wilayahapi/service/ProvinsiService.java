package com.anya.wilayahapi.service;

import com.anya.wilayahapi.entity.Provinsi;

public interface ProvinsiService {

    public default Integer setDelete(boolean status_isDelete) {

        if (status_isDelete) {
            return 0;
        }
        return 1;
    };

    public default void setDelete(Provinsi provinsi, boolean status_isDelete) {
        if (status_isDelete) {
            provinsi.setIs_deleted(0);
        } else {
            provinsi.setIs_deleted(1);
        }

    };

    public default void setData(String kode, String nama, Provinsi provinsi, Integer isDelete) {
        provinsi.setIs_deleted(setDelete(false));
        provinsi.setKode(kode);
        provinsi.setNama(nama);
    };

}
