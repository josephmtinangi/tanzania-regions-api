package io.github.josephmtinangi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.josephmtinangi.models.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {

	public Ward findByDistrictIdAndSlug(Long id, String wardSlug);

}
