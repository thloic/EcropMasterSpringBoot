package yenduGroup.EcropMaster.DTO;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CultureDto {
    private Long id;
    private String nom;
    private String moisSemis;
    private Date dateRecolte;
    private String cultureType;
    private float surface;
    private String etat;
    private String rendement;
    private String intrant;
    private String nouvelleCulture;
    private String photo;
}
