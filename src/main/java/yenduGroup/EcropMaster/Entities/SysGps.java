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
@Table(name = "SysGps")

public class SysGps implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float superficie;

    @OneToOne
    @JoinColumn(name = "parcelle_id")
    private Parcelle parcelle;
}
