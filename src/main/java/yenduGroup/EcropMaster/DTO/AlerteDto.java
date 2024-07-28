package yenduGroup.EcropMaster.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlerteDto {
    private Long id;
    private String message;
    private Date date;
    private CampagneDto campagne;
}
