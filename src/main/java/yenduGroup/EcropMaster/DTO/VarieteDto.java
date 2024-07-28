package yenduGroup.EcropMaster.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VarieteDto {
    private Long id;
    private String nom;
    private CultureDto cultureDto;


}
