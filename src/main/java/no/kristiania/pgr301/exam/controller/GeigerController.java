package no.kristiania.pgr301.exam.controller;

import lombok.RequiredArgsConstructor;
import no.kristiania.pgr301.exam.converter.GeigerCounterConverter;
import no.kristiania.pgr301.exam.converter.RadiationReadingConverter;
import no.kristiania.pgr301.exam.dto.GeigerCounterDto;
import no.kristiania.pgr301.exam.dto.RadiationReadingDto;
import no.kristiania.pgr301.exam.enums.DeviceType;
import no.kristiania.pgr301.exam.model.GeigerCounter;
import no.kristiania.pgr301.exam.model.RadiationReading;
import no.kristiania.pgr301.exam.repository.GeigerCounterRepository;
import no.kristiania.pgr301.exam.repository.RadiationReadingRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/devices", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeigerController {

  private final GeigerCounterRepository geigerCounterRepository;
  private final RadiationReadingRepository radiationReadingRepository;
  private final GeigerCounterConverter geigerCounterConverter;
  private final RadiationReadingConverter radiationReadingConverter;

  @PostMapping(value = "/")
  public ResponseEntity createGeigerCounter(
      @RequestParam(required = false) String deviceName,
      @RequestParam(required = false) String deviceType) {

    // TODO: 14.10.2019
    //  Move to service class

    GeigerCounter entity = new GeigerCounter();
    entity.setName(deviceName);

    if (deviceType != null) {
      boolean isDeviceType =
          Arrays.stream(DeviceType.values())
              .anyMatch(e -> e.toString().equalsIgnoreCase(deviceType));

      if (!isDeviceType) {
        return ResponseEntity.badRequest().build();
      }

      entity.setType(DeviceType.valueOf(deviceType));
    }

    geigerCounterRepository.save(entity);
    GeigerCounterDto dto = geigerCounterConverter.createFromEntity(entity);

    return ResponseEntity.ok(dto);
  }

  @GetMapping("/")
  public ResponseEntity getAllDevices() {
    List<GeigerCounter> all = geigerCounterRepository.findAll();
    return ResponseEntity.ok(all);
  }

  @PostMapping(value = "/{deviceId}/measurements")
  public ResponseEntity addRadiationReading(
      @PathParam("deviceId") Long deviceId, @RequestBody RadiationReadingDto radiationReadingDto) {

    // TODO: 14.10.2019
    //  Move to service class
    Optional<GeigerCounter> geigerCounter = geigerCounterRepository.findById(deviceId);

    if (geigerCounter.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    RadiationReading radiationReading =
        radiationReadingConverter.createFromDto(radiationReadingDto);
    radiationReading.setDeviceId(deviceId);

    radiationReadingRepository.save(radiationReading);

    return ResponseEntity.status(201).build();
  }

  @GetMapping(value = "/{deviceId}/measurements")
  public ResponseEntity getReadings(@PathParam("deviceId") Long deviceId) {

    Optional<GeigerCounter> geigerCounter = geigerCounterRepository.findById(deviceId);

    if (geigerCounter.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    List<RadiationReading> radiationReadings = geigerCounter.get().getRadiationReadings();

    return ResponseEntity.ok(radiationReadings);
  }
}
