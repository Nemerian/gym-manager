package com.radu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity // This tells Hibernate to make a table out of this class
public class Pachet {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String denumire;
	private Integer durata;
	private Integer nrsedinte;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datastart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datasfarsit;
	private Integer valoare;
	private String observatii;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenumire() {
		return this.denumire;
	}

	public void setDenumire(String Denumire) {
		this.denumire = Denumire;
	}

	public Integer getDurata() {
		return this.durata;
	}

	public void setDurata(Integer Durata) {
		this.durata = Durata;
	}

	public Integer getNrsedinte() {
		return this.nrsedinte;
	}

	public void setNrsedinte(Integer Nrsedinte) {
		this.nrsedinte = Nrsedinte;
	}

	public Date getDatastart() {
		return this.datastart;
	}

	public void setDatastart(Date DataStart) {
		this.datastart = DataStart;
	}

	public Date getDatasfarsit() {
		return this.datasfarsit;
	}

	public void setDatasfarsit(Date DataSfarsit) {
		this.datasfarsit = DataSfarsit;
	}

	public Integer getValoare() {
		return this.valoare;
	}

	public void setValoare(Integer Valoare) {
		this.valoare = Valoare;
	}

	public String getObservatii() {
		return this.observatii;
	}

	public void setObservatii(String Observatii) {
		this.observatii = Observatii;
	}
}
