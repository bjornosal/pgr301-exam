package no.kristiania.pgr301.exam.controller;

import no.kristiania.pgr301.exam.enums.DeviceType;
import no.kristiania.pgr301.exam.feature.GeigerFeatures;
import no.kristiania.pgr301.exam.model.GeigerCounter;
import no.kristiania.pgr301.exam.repository.GeigerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<GeigerCounter> getGeigerCounter(
      @RequestParam(required = false) String deviceName,
      @RequestParam(required = false) String deviceType) {
    GeigerCounter model = new GeigerCounter();

    if (GeigerFeatures.NAMING_FEATURE.isActive() && deviceName != null) {
      model.setName(deviceName);
    }

    if (GeigerFeatures.TYPE_FEATURE.isActive() && deviceType != null) {
      boolean isDeviceType =
          Arrays.stream(DeviceType.values())
              .anyMatch(e -> e.toString().equalsIgnoreCase(deviceType));

      if (!isDeviceType) {
        return ResponseEntity.badRequest().build();
      }

      model.setType(DeviceType.valueOf(deviceType));
    }

    repository.save(model);
    return ResponseEntity.ok(model);
  }
}
