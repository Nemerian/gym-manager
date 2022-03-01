package com.radu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity // This tells Hibernate to make a table out of this class
public class Istoric {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@NotNull
	private Long idmembru;
	@NotNull
	private Long idtippachet;
	private String numemembru;
	private String denumirepachet;
	private Date datastart;
	private Date datasfarsit;
	private Integer valoare;
	private String observatii;

	public Istoric() {
	}

	public Istoric(Long id, Long idmembru, Long IdTipPachet, String NumeMembru, String DenumirePachet, Date DataStart, Date DataSfarsit, Integer Valoare, String Observatii) {
		this.id = id;
		this.idmembru = idmembru;
		this.idtippachet = IdTipPachet;
		this.numemembru = NumeMembru;
		this.denumirepachet = DenumirePachet;
		this.datastart = DataStart;
		this.datasfarsit = DataSfarsit;
		this.valoare = Valoare;
		this.observatii = Observatii;
	}
	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdmembru() {
		return this.idmembru;
	}

	public void setIdmembru(Long IdMembru) {
		this.idmembru = IdMembru;
	}

	public Long getIdtippachet() {
		return this.idtippachet;
	}

	public void setIdtippachet(Long IdTipPachet) {
		this.idtippachet = IdTipPachet;
	}
	public String getNumemembru() {
		return this.numemembru;
	}

	public void setNumemembru(String NumeMembru) {
		this.numemembru = NumeMembru;
	}

	public String getDenumirepachet() {
		return this.denumirepachet;
	}

	public void setDenumirepachet(String DenumirePachet) {
		this.denumirepachet = DenumirePachet;
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
