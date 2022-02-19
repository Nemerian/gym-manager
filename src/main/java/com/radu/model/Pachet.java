package com.radu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Pachet {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private String Denumire;
	private Integer Durata;
	private Integer NrSedinte;
	private Date DataStart;
	private Date DataSfarsit;
	private Integer Valoare;
	private String Observatii;

	public Pachet(){
	}
	
	public Pachet(Long id, String Denumire, Integer Durata, Integer NrSedinte, Date DataStart, Date DataSfarsit, Integer Valoare, String Observatii) {
		this.id = id;
		this.Denumire = Denumire;
		this.Durata = Durata;
		this.NrSedinte = NrSedinte;
		this.DataStart = DataStart;
		this.DataSfarsit = DataSfarsit;
		this.Valoare = Valoare;
		this.Observatii = Observatii;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenumire() {
		return this.Denumire;
	}

	public void setDenumire(String Denumire) {
		this.Denumire = Denumire;
	}

	public Integer getDurata() {
		return this.Durata;
	}

	public void setDurata(Integer Durata) {
		this.Durata = Durata;
	}

	public Integer getNrSedinte() {
		return this.NrSedinte;
	}

	public void setNrSedinte(Integer NrSedinte) {
		this.NrSedinte = NrSedinte;
	}

	public Date getDataStart() {
		return this.DataStart;
	}

	public void setDataStart(Date DataStart) {
		this.DataStart = DataStart;
	}

	public Date getDataSfarsit() {
		return this.DataSfarsit;
	}

	public void setDataSfarsit(Date DataSfarsit) {
		this.DataSfarsit = DataSfarsit;
	}

	public Integer getValoare() {
		return this.Valoare;
	}

	public void setValoare(Integer Valoare) {
		this.Valoare = Valoare;
	}

	public String getObservatii() {
		return this.Observatii;
	}

	public void setObservatii(String Observatii) {
		this.Observatii = Observatii;
	}
}
