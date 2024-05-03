package com.Prcato.Service;

import com.Prcato.Entity.Patient;
import com.Prcato.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository ;

    public Patient createPatient(Patient patient){
        Patient savedPatient= patientRepository.save(patient);
        return patient;
    }
}