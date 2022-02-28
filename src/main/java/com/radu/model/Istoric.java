package com.radu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Istoric {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private Long idmembru;
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

	public Long getIdMembru() {
		return this.idmembru;
	}

	public void setIdMembru(Long IdMembru) {
		this.idmembru = IdMembru;
	}

	public Long getIdTipPachet() {
		return this.idtippachet;
	}

	public void setIdTipPachet(Long IdTipPachet) {
		this.idtippachet = IdTipPachet;
	}

	public String getNumeMembru() {
		return this.numemembru;
	}

	public void setNumeMembru(String NumeMembru) {
		this.numemembru = NumeMembru;
	}

	public String getDenumirePachet() {
		return this.denumirepachet;
	}

	public void setDenumirePachet(String DenumirePachet) {
		this.denumirepachet = DenumirePachet;
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
