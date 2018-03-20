package io.github.josephmtinangi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.josephmtinangi.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

	public Region findFirstBySlug(String slug);

}
