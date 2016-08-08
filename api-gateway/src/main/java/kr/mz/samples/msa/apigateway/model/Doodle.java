package kr.mz.samples.msa.apigateway.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Doodle {

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