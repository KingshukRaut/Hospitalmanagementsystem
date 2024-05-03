package com.Prcato.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name= "username",length=28, nullable = false, unique = true)
    private String username;
  @Column(name="EmailId",length = 128 ,nullable = false, unique = true)
    private String EmailId;
    private String password;

}
