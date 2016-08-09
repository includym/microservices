package kr.mz.samples.msa.toilet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Toilet {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	public Toilet() {
	}
	
	public Toilet(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Toilet [id=" + id + ", name=" + name + "]";
	}

}