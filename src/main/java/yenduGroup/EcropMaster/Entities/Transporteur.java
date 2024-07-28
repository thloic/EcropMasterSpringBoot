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
@Table(name = "Transporteur")

public class Transporteur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateTransport;
    private float quantite;

    @OneToMany(mappedBy = "transporteur")
    private Set<LotProduit> lotProduits;
}
