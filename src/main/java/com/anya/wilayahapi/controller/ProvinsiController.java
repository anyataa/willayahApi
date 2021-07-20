package com.anya.wilayahapi.controller;

import java.util.List;

import com.anya.wilayahapi.dto.ProvinsiDto;
import com.anya.wilayahapi.dto.ResourceNotFoundException;
import com.anya.wilayahapi.entity.Provinsi;
import com.anya.wilayahapi.repository.ProvinsiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provinsi")
public class ProvinsiController {

    @Autowired
    ProvinsiRepository repo;

    @GetMapping
    public List<Provinsi> getAllProvinsi() {
        return repo.findAll();
    }

    @GetMapping("/{id_provinsi}")
    public ResponseEntity<Provinsi> getProvinsiById(@PathVariable(value = "id_provinsi") Integer id_provinsi)
            throws ResourceNotFoundException {
        Provinsi foundProvinsi = repo.findById(id_provinsi).orElseThrow(
                () -> new ResourceNotFoundException("Provinsi with id : " + id_provinsi + " can not be found"));
        return ResponseEntity.ok().body(foundProvinsi);
    }

    @PostMapping
    public ResponseEntity<?> postProvinsi(@RequestBody ProvinsiDto dto) {
        Provinsi newProvinsi = new Provinsi();
        dto.setIs_deleted(dto.setDelete(false));
        dto.setData(dto.getKode(), dto.getNama(), newProvinsi, dto.getIs_deleted());
        repo.save(newProvinsi);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id_provinsi}")
    public ResponseEntity<?> putProvinsi(@PathVariable(value = "id_provinsi") Integer id_provinsi,
            @RequestBody ProvinsiDto dto) throws ResourceNotFoundException {

        Provinsi foundProvinsi = repo.findById(id_provinsi).orElseThrow(
                () -> new ResourceNotFoundException("Provinsi with id : " + id_provinsi + " can not be found"));
        dto.setData(dto.getKode(), dto.getNama(), foundProvinsi, 1);
        repo.save(foundProvinsi);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id_provinsi}")
    public ResponseEntity<?> deleteProvinsi(@PathVariable(value = "id_provinsi") Integer id_provinsi, ProvinsiDto dto)
            throws ResourceNotFoundException {
        Provinsi foundProvinsi = repo.findById(id_provinsi).orElseThrow(
                () -> new ResourceNotFoundException("Provinsi with id : " + id_provinsi + " can not be found"));
        dto.setDelete(foundProvinsi, true);
        repo.save(foundProvinsi);
        return ResponseEntity.ok().body(foundProvinsi);
    }

    @GetMapping("/active")
    public List<Provinsi> getActiveProvinsi() {
        return repo.getActiveProvinsi();
    }

}
