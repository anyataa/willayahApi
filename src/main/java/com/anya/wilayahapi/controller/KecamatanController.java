package com.anya.wilayahapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.anya.wilayahapi.dto.KecamatanDto;
import com.anya.wilayahapi.dto.KotaDto;
import com.anya.wilayahapi.dto.ResourceNotFoundException;
import com.anya.wilayahapi.entity.Kecamatan;
import com.anya.wilayahapi.entity.Kota;
import com.anya.wilayahapi.repository.KecamatanRepository;
import com.anya.wilayahapi.repository.KotaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/kecamatan")
public class KecamatanController {

    @Autowired
    KecamatanRepository repo;

    @Autowired
    KotaRepository kotarepo;

    @GetMapping
    public List<Kecamatan> getMethodName() {
        return repo.findAll();
    }

    @GetMapping(value = "{id_kecamatan}")
    public ResponseEntity<?> getKecamatanById(@PathVariable(value = "id_kecamatan") Integer id_kecamatan)
            throws ResourceNotFoundException {
        Kecamatan foundKecamatan = repo.findById(id_kecamatan)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find kecamatan with id : " + id_kecamatan));

        return ResponseEntity.ok().body(foundKecamatan);
    }

    @PostMapping
    public ResponseEntity<?> postKecamatan(@RequestBody KecamatanDto dto) {
        Kecamatan newKecamatan = new Kecamatan();
        Kota foundKota = kotarepo.findByKode(dto.getKodeKota());
        dto.setData(newKecamatan, dto.getNama(), dto.getKode(), foundKota);
        repo.save(newKecamatan);
        return ResponseEntity.ok().body(newKecamatan);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> putKecamatan(@PathVariable(value = "id") Integer id_kecamatan,
            @RequestBody KecamatanDto dto) throws ResourceNotFoundException {
        // TODO: process PUT request

        Kecamatan foundkecamatan = repo.findById(id_kecamatan)
                .orElseThrow(() -> new ResourceNotFoundException("Can not found kecamatan with id  : " + id_kecamatan));
        Kota foundKota = kotarepo.findByKode(dto.getKodeKota());
        dto.setData(foundkecamatan, dto.getNama(), dto.getKode(), foundKota);
        repo.save(foundkecamatan);
        return ResponseEntity.ok().body(foundkecamatan);
    }

    @DeleteMapping("/{id_kecamatan}")
    public ResponseEntity<?> deleteKecamatan(@PathVariable("id_kecamatan") Integer id_kecamatan, KecamatanDto dto)
            throws ResourceNotFoundException {
        Kecamatan foundKecamatan = repo.findById(id_kecamatan).orElseThrow(
                () -> new ResourceNotFoundException("Kecamatan with id : " + id_kecamatan + "is not exisit"));
        dto.setDelete(foundKecamatan, true);
        return ResponseEntity.ok().body(foundKecamatan);

    };

    @GetMapping(value = "/active")
    public List<Kecamatan> getActiveKecamatan() {
        return repo.getActiveKecamatan();
    }

    @GetMapping(value = "/kota")
    public List<?> getByKodeKota(@RequestParam(value = "kodeKota") String kodeKota) {
        return repo.getbyKodeKota(kodeKota);
    }

}
