package com.anya.wilayahapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.anya.wilayahapi.dto.DesaDto;
import com.anya.wilayahapi.dto.ResourceNotFoundException;
import com.anya.wilayahapi.entity.Desa;
import com.anya.wilayahapi.entity.Kecamatan;
import com.anya.wilayahapi.repository.DesaRepository;
import com.anya.wilayahapi.repository.KecamatanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/desa")
public class DesaController {

    @Autowired
    DesaRepository repo;
    @Autowired
    KecamatanRepository kecamatanRepo;

    @GetMapping
    public List<Desa> getDesa() {
        return repo.findAll();
    }

    @GetMapping("/{id_desa}")
    public ResponseEntity<?> getDesa(@PathVariable("id_desa") Integer id_desa) throws ResourceNotFoundException {
        Desa foundDesa = repo.findById(id_desa)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find desa with id : " + id_desa));

        return ResponseEntity.ok().body(foundDesa);
    }

    @PostMapping
    public ResponseEntity<?> postDesa(@RequestBody DesaDto dto) {
        Desa newDesa = new Desa();
        Kecamatan foundKecamatan = kecamatanRepo.findByKode(dto.getKodeKecamatan());
        dto.setData(newDesa, dto.getNama(), dto.getKode(), foundKecamatan);
        repo.save(newDesa);
        return ResponseEntity.ok().body(newDesa);

    }

    @PutMapping(value = "/{id_desa}")
    public ResponseEntity<?> putDesa(@PathVariable("id_desa") Integer id_desa, @RequestBody DesaDto dto)
            throws ResourceNotFoundException {
        // TODO: process PUT request

        Kecamatan foundKecamatan = kecamatanRepo.findByKode(dto.getKodeKecamatan());
        Desa foundDesa = repo.findById(id_desa)
                .orElseThrow(() -> new ResourceNotFoundException("Desa with id : " + id_desa + "cannot be found"));

        dto.setData(foundDesa, dto.getNama(), dto.getKode(), foundKecamatan);
        repo.save(foundDesa);
        return ResponseEntity.ok().body(foundDesa);
    }

    @GetMapping(value = "/active")
    public List<Desa> getActiveDesa() {
        return repo.getActiveDesa();
    }

    @DeleteMapping("/{id_desa}")
    public ResponseEntity<?> deleteDesa(@PathVariable("id_desa") Integer id_desa, DesaDto dto)
            throws ResourceNotFoundException {
        // TODO: process PUT request
        Desa foundDesa = repo.findById(id_desa)
                .orElseThrow(() -> new ResourceNotFoundException("Desa with id : " + id_desa + "cannot be found"));
        dto.setDelete(foundDesa, true);
        repo.save(foundDesa);
        return ResponseEntity.ok().body(foundDesa);
    }

    @GetMapping("/kecamatan")
    public List<?> getByKecamatan(@RequestParam("kodeKecamatan") String kodeKecamatan) {

        return repo.getbyKodeKecamatan(kodeKecamatan);

    }

}
