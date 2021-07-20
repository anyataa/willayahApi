package com.anya.wilayahapi.controller;

import java.util.List;

import com.anya.wilayahapi.dto.KotaDto;
import com.anya.wilayahapi.dto.ResourceNotFoundException;
import com.anya.wilayahapi.entity.Kota;
import com.anya.wilayahapi.entity.Provinsi;
import com.anya.wilayahapi.repository.KotaRepository;
import com.anya.wilayahapi.repository.ProvinsiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/kota")
public class KotaController {
    @Autowired
    KotaRepository repo;

    @Autowired
    ProvinsiRepository provinsiRepo;

    @GetMapping
    public List<Kota> getAllKota() {
        return repo.findAll();

    }

    @GetMapping("/{id_kota}")
    public ResponseEntity<?> getKotabyId(@PathVariable(value = "id_kota") Integer id_kota)
            throws ResourceNotFoundException {
        Kota foundKota = repo.findById(id_kota)
                .orElseThrow(() -> new ResourceNotFoundException("Can not found kota with id : " + id_kota));
        return ResponseEntity.ok().body(foundKota);

    }

    @PutMapping("/{id_kota}")
    public ResponseEntity<?> putKota(@PathVariable(value = "id_kota") Integer id_kota, @RequestBody KotaDto dto)
            throws ResourceNotFoundException {
        Kota foundKota = repo.findById(id_kota)
                .orElseThrow(() -> new ResourceNotFoundException("Can not found kota with id : " + id_kota));
        Provinsi foundProvinsi = provinsiRepo.findByKode(dto.getKodeProvinsi());
        dto.setData(foundKota, dto.getKode(), dto.getNama(), foundProvinsi);
        repo.save(foundKota);
        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping("/{id_kota}")
    public ResponseEntity<?> deleteKota(@PathVariable("id_kota") Integer id_kota, KotaDto dto)
            throws ResourceNotFoundException {
        Kota foundKota = repo.findById(id_kota)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot found kota with id : " + id_kota));
        dto.setDelete(foundKota, true);
        repo.save(foundKota);
        return ResponseEntity.ok().body(foundKota);

    }

    @PostMapping
    public ResponseEntity<Kota> postKota(@RequestBody KotaDto dto) {
        // TODO: process POST request
        Provinsi newProvinsi = provinsiRepo.findByKode(dto.getKodeProvinsi());
        Kota newKota = new Kota();
        dto.setData(newKota, dto.getKode(), dto.getNama(), newProvinsi);
        repo.save(newKota);
        return ResponseEntity.ok().body(newKota);
    }

    @GetMapping("/provinsi")
    public List<?> getByProvinsi(@RequestParam("kodeProvinsi") String kodeProvinsi) {
        return repo.getByProvinsi(kodeProvinsi);

    }

    @GetMapping("/active")
    public List<Kota> getActive() {
        return repo.getActiveKota();
    }

}
