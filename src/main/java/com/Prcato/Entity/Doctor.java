package com.Prcato.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
;import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name",length = 48,nullable = false)
    private String name;
    @Column(name="qualification",length= 48, nullable = false)
    private String qualification;
    @Column(name="specialization", length= 128, nullable = false)
    private String Specializations;
    @Column(name="experience", length=48 , nullable = false )
    private String experience;
    @Column(name="description", length=250, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.DOCTOR;

}
