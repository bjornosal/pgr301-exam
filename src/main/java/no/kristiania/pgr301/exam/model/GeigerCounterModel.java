package no.kristiania.pgr301.exam.model;

import lombok.Data;
import no.kristiania.pgr301.exam.enums.DeviceType;

import javax.persistence.*;

@Entity
@Data
public class GeigerCounterModel {

  @Id @GeneratedValue private Long id;

  private String name;

  @Enumerated(value = EnumType.STRING)
  private DeviceType type;
}
