package com.Prcato.Payload;

import com.Prcato.Entity.Doctor;
import com.Prcato.Entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private String name;
    private String qualification;
    private String specializations;
    private String experience;
    private String description;
    private Doctor doctor;
    private List<Review> reviews;
    private double ratingPercentage;
}

