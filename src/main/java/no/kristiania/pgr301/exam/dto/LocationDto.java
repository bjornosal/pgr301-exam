package no.kristiania.pgr301.exam.dto;

import lombok.Data;
import no.kristiania.pgr301.exam.model.Location;

import java.time.LocalDateTime;

@Data
public class LocationDto {

  private Long id;
  private double latitude;
  private double longitude;
  private LocalDateTime timestamp;

  public static LocationDto fromEntity(Location location) {
    LocationDto dto = new LocationDto();
    dto.setId(location.getId());
    dto.setLatitude(location.getLatitude());
    dto.setLongitude(location.getLongitude());
    dto.setTimestamp(location.getTimestamp());
    return dto;
  }
}
