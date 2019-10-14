package no.kristiania.pgr301.exam.dto;

import lombok.Data;
import no.kristiania.pgr301.exam.enums.DeviceType;
import no.kristiania.pgr301.exam.model.GeigerCounter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class GeigerCounterDto {

  private Long deviceId;
  private String name;
  private DeviceType type;
  private List<RadiationReadingDto> radiationReadings;

  public static GeigerCounterDto createFromEntity(GeigerCounter geigerCounter) {
    GeigerCounterDto geigerCounterDto = new GeigerCounterDto();
    geigerCounterDto.setDeviceId(geigerCounter.getId());
    geigerCounterDto.setName(geigerCounter.getName());
    geigerCounterDto.setType(geigerCounterDto.getType());

    if (geigerCounter.getRadiationReadings() != null) {

      List<RadiationReadingDto> radiationReadingDtos =
          geigerCounter.getRadiationReadings().stream()
              .filter(Objects::nonNull)
              .map(RadiationReadingDto::createFromEntity)
              .collect(Collectors.toList());
      geigerCounterDto.setRadiationReadings(radiationReadingDtos);
    }

    return geigerCounterDto;
  }
}
