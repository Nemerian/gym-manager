package com.radu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Pachet implements Serializable {

    private static final long serialVersionUID = -8582553475226281591L;
    // private static final long serialVersionUID = -91969758749726312L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String denumire;
	private Integer durata;
	private Integer nrsedinte;
	private Date datastart;
	private Date datasfarsit;
	private Integer valoare;
	private String observatii;

	public Pachet(){
	}
	
	public Pachet(Long id, String Denumire, Integer Durata, Integer NrSedinte, Date DataStart, Date DataSfarsit, Integer Valoare, String Observatii) {
		this.id = id;
		this.denumire = Denumire;
		this.durata = Durata;
		this.nrsedinte = NrSedinte;
		this.datastart = DataStart;
		this.datasfarsit = DataSfarsit;
		this.valoare = Valoare;
		this.observatii = Observatii;
	}


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

	public Integer getNrSedinte() {
		return this.nrsedinte;
	}

	public void setNrSedinte(Integer NrSedinte) {
		this.nrsedinte = NrSedinte;
	}

	public Date getDataStart() {
		return this.datastart;
	}

	public void setDataStart(Date DataStart) {
		this.datastart = DataStart;
	}

	public Date getDataSfarsit() {
		return this.datasfarsit;
	}

	public void setDataSfarsit(Date DataSfarsit) {
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
