package io.github.josephmtinangi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.josephmtinangi.models.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

}
