package kr.mz.samples.msa.doodle.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.mz.samples.msa.doodle.model.Doodle;

public interface DoodleRepository extends JpaRepository<Doodle, Long> {

	Page<Doodle> findByToiletId(Long toiletId, Pageable pageable);

}
