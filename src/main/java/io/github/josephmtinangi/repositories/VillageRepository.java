package io.github.josephmtinangi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.josephmtinangi.models.Village;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {

}
