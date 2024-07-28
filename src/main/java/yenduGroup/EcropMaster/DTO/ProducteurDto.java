package yenduGroup.EcropMaster.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import yenduGroup.EcropMaster.Entities.Cooperative;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class ProducteurDto {
    private Long id;
    private String nom;
    private String prenom;
    private int telephone;
    private String email;
    private String adresse;
    private Date dateNaissance;
    private Date dateAdhesion;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int RangDadhesion;
    private Set<Cooperative> cooperatives;

}
