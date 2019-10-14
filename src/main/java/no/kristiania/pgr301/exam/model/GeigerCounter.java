package no.kristiania.pgr301.exam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.kristiania.pgr301.exam.enums.DeviceType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeigerCounter implements Serializable {

  @Id @GeneratedValue private Long id;

  private String name;

  @Enumerated(value = EnumType.STRING)
  private DeviceType type;

  private List<Double> radiationReadings;

  private List<Location> location;
}
