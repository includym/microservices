package kr.mz.samples.msa.doodle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.mz.samples.msa.doodle.model.Doodle;
import kr.mz.samples.msa.doodle.repository.DoodleRepository;

@Service
public class DoodleService {

	@Autowired
	private DoodleRepository doodleRepository;
	
	public Page<Doodle> findAll(Pageable pageable) {
		return doodleRepository.findAll(pageable);
	}

	public Page<Doodle> findByToiletId(Long toiletId, Pageable pageable) {
		return doodleRepository.findByToiletId(toiletId, pageable);
	}

}
