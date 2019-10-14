package no.kristiania.pgr301.exam.repository;

import no.kristiania.pgr301.exam.model.RadiationReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadiationReadingRepository extends JpaRepository<RadiationReading, Long> {}
