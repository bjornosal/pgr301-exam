package no.kristiania.pgr301.exam.dto;

import lombok.Data;
import no.kristiania.pgr301.exam.model.RadiationReading;

import java.time.LocalDateTime;

@Data
public class RadiationReadingDto {

  private Long id;
  private double latitude;
  private double longitude;
  private LocalDateTime timestamp;

  public static RadiationReadingDto createFromEntity(RadiationReading radiationReading) {
    RadiationReadingDto dto = new RadiationReadingDto();
    dto.setId(radiationReading.getId());
    dto.setLatitude(radiationReading.getLatitude());
    dto.setLongitude(radiationReading.getLongitude());
    dto.setTimestamp(radiationReading.getTimestamp());
    return dto;
  }
}
