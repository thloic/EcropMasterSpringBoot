package yenduGroup.EcropMaster.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Entity
@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LotProduit")

public class LotProduit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numLot;
    private String description;

    @OneToOne
    @JoinColumn(name = "recolte_id")
    private Recolte recolte;

    @ManyToOne
    @JoinColumn(name = "transporteur_id")
    private Transporteur transporteur;
}
