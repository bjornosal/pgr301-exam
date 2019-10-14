package no.kristiania.pgr301.exam.controller;

import no.kristiania.pgr301.exam.dto.GeigerCounterDto;
import no.kristiania.pgr301.exam.enums.DeviceType;
import no.kristiania.pgr301.exam.model.GeigerCounter;
import no.kristiania.pgr301.exam.repository.GeigerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/devices", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeigerController {

  private final GeigerRepository repository;

  @Autowired
  public GeigerController(GeigerRepository repository) {
    this.repository = repository;
  }

  @PostMapping(value = "/")
  public ResponseEntity getGeigerCounter(
      @RequestParam(required = false) String deviceName,
      @RequestParam(required = false) String deviceType) {
    GeigerCounter model = new GeigerCounter();

    model.setName(deviceName);

    if (deviceType != null) {
      boolean isDeviceType =
          Arrays.stream(DeviceType.values())
              .anyMatch(e -> e.toString().equalsIgnoreCase(deviceType));

      if (!isDeviceType) {
        return ResponseEntity.badRequest().build();
      }

      model.setType(DeviceType.valueOf(deviceType));
    }

    repository.save(model);
    GeigerCounterDto dto = GeigerCounterDto.createFromEntity(model);

    return ResponseEntity.ok(dto);
  }
}
