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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/devices")
public class GeigerController {

  private final GeigerCounterRepository geigerCounterRepository;
  private final RadiationReadingRepository radiationReadingRepository;
  private final GeigerCounterConverter geigerCounterConverter;
  private final RadiationReadingConverter radiationReadingConverter;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createGeigerCounter(
      @RequestParam(required = false) String deviceName,
      @RequestParam(required = false) String deviceType) {

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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getAllDevices() {
    List<GeigerCounter> geigerCounters = geigerCounterRepository.findAll();
    return ResponseEntity.ok(geigerCounterConverter.createFromEntities(geigerCounters));
  }

  @PostMapping(value = "/{deviceId}/measurements", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addRadiationReading(
      @PathVariable("deviceId") Long deviceId,
      @RequestBody RadiationReadingDto radiationReadingDto) {

    if (deviceId == null) {
      return ResponseEntity.notFound().build();
    }

    Optional<GeigerCounter> geigerCounter = geigerCounterRepository.findById(deviceId);

    if (geigerCounter.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    RadiationReading radiationReading =
        radiationReadingConverter.createFromDto(radiationReadingDto);
    radiationReading.setDevice(geigerCounter.get());

    radiationReadingRepository.save(radiationReading);

    return ResponseEntity.status(201).build();
  }

  @GetMapping(value = "/{deviceId}/measurements", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getReadings(@PathVariable("deviceId") Long deviceId) {

    Optional<GeigerCounter> geigerCounter = geigerCounterRepository.findById(deviceId);

    if (geigerCounter.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    List<RadiationReading> radiationReadings = geigerCounter.get().getRadiationReadings();

    return ResponseEntity.ok(radiationReadingConverter.createFromEntities(radiationReadings));
  }
}
