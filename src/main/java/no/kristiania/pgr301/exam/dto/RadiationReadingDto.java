package no.kristiania.pgr301.exam.dto;

import lombok.Data;
import no.kristiania.pgr301.exam.model.RadiationReading;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RadiationReadingDto {

  private Long id;
  @NotNull private Double latitude;
  @NotNull private Double longitude;
  @NotNull private Double sievert;
  @NotNull private LocalDateTime timestamp;

}
