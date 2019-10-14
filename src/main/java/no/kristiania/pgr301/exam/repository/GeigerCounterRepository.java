package no.kristiania.pgr301.exam.repository;

import no.kristiania.pgr301.exam.model.GeigerCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeigerCounterRepository extends JpaRepository<GeigerCounter, Long> {}
