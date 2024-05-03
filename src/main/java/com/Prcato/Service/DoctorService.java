package com.Prcato.Service;

import com.Prcato.Entity.Doctor;
import com.Prcato.Exception.ResourceNotFoundException;
import com.Prcato.Payload.DoctorDto;
import com.Prcato.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorDto addDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setQualification(doctorDto.getQualification());
        doctor.setExperience(doctorDto.getExperience());
        doctor.setSpecializations(doctorDto.getSpecializations());
        doctor.setDescription(doctorDto.getDescription());
        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToDto(savedDoctor);
    }

    public List<Doctor> searchDoctorsByNameOrSpecializations(String search) {
        return doctorRepository.searchByNameOrSpecializations(search);
    }

    public DoctorDto getDoctorById(long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        return convertToDto(doctor);
    }

    public DoctorDto updateDoctor(long id, DoctorDto doctorDto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        doctor.setName(doctorDto.getName());
        doctor.setExperience(doctorDto.getExperience());
        doctor.setQualification(doctorDto.getQualification());
        doctor.setSpecializations(doctorDto.getSpecializations());
        doctor.setDescription(doctorDto.getDescription());
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return convertToDto(updatedDoctor);
    }

    public void deleteDoctorById(long id) {
        doctorRepository.deleteById(id);
    }

    private DoctorDto convertToDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setName(doctor.getName());
        doctorDto.setQualification(doctor.getQualification());
        doctorDto.setSpecializations(doctor.getSpecializations());
        doctorDto.setExperience(doctor.getExperience());
        doctorDto.setDescription(doctor.getDescription());
        return doctorDto;
    }
}
