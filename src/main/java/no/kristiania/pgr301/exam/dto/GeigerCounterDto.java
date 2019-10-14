package no.kristiania.pgr301.exam.dto;

import lombok.Data;
import no.kristiania.pgr301.exam.enums.DeviceType;
import no.kristiania.pgr301.exam.model.GeigerCounter;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GeigerCounterDto {

  private Long deviceId;
  private String name;
  private DeviceType type;
  private List<Double> radiationReadings;
  private List<LocationDto> locationInfo;

  public static GeigerCounterDto fromEntity(GeigerCounter geigerCounter) {
    GeigerCounterDto geigerCounterDto = new GeigerCounterDto();
    geigerCounterDto.setDeviceId(geigerCounter.getId());
    geigerCounterDto.setName(geigerCounter.getName());
    geigerCounterDto.setType(geigerCounterDto.getType());

    geigerCounterDto.getRadiationReadings().addAll(geigerCounter.getRadiationReadings());

    List<LocationDto> locationInfo =
        geigerCounter.getLocation().stream()
            .map(LocationDto::fromEntity)
            .collect(Collectors.toList());

    geigerCounterDto.setLocationInfo(locationInfo);
    return geigerCounterDto;
  }
}
