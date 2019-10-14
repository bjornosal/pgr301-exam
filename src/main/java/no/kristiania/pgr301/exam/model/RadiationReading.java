package no.kristiania.pgr301.exam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RadiationReading implements Serializable {

  @Id @GeneratedValue private Long id;
  private Double latitude;
  private Double longitude;
  private Double sievert;
  @CreationTimestamp private LocalDateTime timestamp;
  @UpdateTimestamp private LocalDateTime lastUpdated;
}
