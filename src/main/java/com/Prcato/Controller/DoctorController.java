package com.Prcato.Controller;
import com.Prcato.Entity.Doctor;
import com.Prcato.Payload.DoctorDto;
import com.Prcato.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @PostMapping
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody DoctorDto doctorDto){
        DoctorDto savedDoctor= doctorService.addDoctor(doctorDto);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }
    @GetMapping("/search")
    public List<Doctor> searchDoctors(@RequestParam String search) {
        return doctorService.searchDoctorsByNameOrSpecializations(search);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable long id){
       DoctorDto doctorDto= doctorService.getDoctorById(id);
       return new ResponseEntity<>(doctorDto,HttpStatus.OK);
    }
   @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor
            (@RequestBody DoctorDto doctorDto,@PathVariable long id){
          DoctorDto updateDoctor= doctorService.updateDoctor(id,doctorDto);
          return new ResponseEntity<>(updateDoctor,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteDoctor(@PathVariable long id){
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>("Doctor entity deleted Successfully",HttpStatus.OK);

     }

        }
