package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;
// test commment

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    String idReservation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date anneeUniversitaire;
    boolean estValide;


    @ManyToMany
    Set<Etudiant> etudiants;

}


