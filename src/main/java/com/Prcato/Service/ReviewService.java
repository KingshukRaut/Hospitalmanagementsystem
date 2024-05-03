package com.Prcato.Service;

import com.Prcato.Entity.Doctor;
import com.Prcato.Entity.Patient;
import com.Prcato.Entity.Review;
import com.Prcato.Exception.ResourceNotFoundException;
import com.Prcato.Repository.DoctorRepository;
import com.Prcato.Repository.PatientRepository;
import com.Prcato.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review){
        Optional<Doctor> findDoctor = doctorRepository.findById(review.getDoctorId());
        Doctor doctor = findDoctor.orElseThrow(() ->
                new ResourceNotFoundException("Doctor","id",review.getDoctorId()));

        Optional<Patient> findPatientId = patientRepository.findById(review.getPatientId());
        Patient patient= findPatientId.orElseThrow(()->
                new ResourceNotFoundException("Patient","id",review.getPatientId()));

         Review savedReview =null;

         if(doctor!= null || patient!= null){
             savedReview = reviewRepository.save(review);
        }
        return savedReview;
    }

    public List<Review> getReviewByDoctorId(long doctorId){
        List<Review> reviews = reviewRepository.findByDoctorId(doctorId);
        return reviews;

    }
}
