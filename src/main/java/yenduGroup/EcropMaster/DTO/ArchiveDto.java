package yenduGroup.EcropMaster.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchiveDto {
    private Long id;
    private int ensCampagne;
    private String ensCulture;
}
