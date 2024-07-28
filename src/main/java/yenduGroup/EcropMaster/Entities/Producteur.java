package yenduGroup.EcropMaster.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Producteur")

public class Producteur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private int telephone;
    private String email;
    private Date dateAdhesion;
    private Date dateNaissance;
    private String adresse;
    private String EntrepriseAppartenance;
    @OneToMany(mappedBy = "producteur")
    private Set<Parcelle> parcelles;
    @ManyToOne
    @JoinColumn(name = "cooperative_id")
    private Cooperative cooperative;



}
