package kr.mz.samples.msa.doodle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Doodle {

	@Id
	@GeneratedValue
	private Long id;

	private Long toiletId;

	private String name;

	public Doodle() {
	}

	public Doodle(Long toiletId, String name) {
		this.toiletId = toiletId;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getToiletId() {
		return toiletId;
	}

	public void setToiletId(Long toiletId) {
		this.toiletId = toiletId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}