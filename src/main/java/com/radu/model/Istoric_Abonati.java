package com.radu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Istoric_Abonati {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private Long IdMembru;
	private Long IdTipPachet;
	private String NumeMembru;
	private String DenumirePachet;
	private Date DataStart;
	private Date DataSfarsit;
	private Integer Valoare;
	private String Observatii;


	public Long getId(){
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMembru() {
		return this.IdMembru;
	}

	public void setIdMembru(Long IdMembru) {
		this.IdMembru = IdMembru;
	}

	public Long getIdTipPachet() {
		return this.IdTipPachet;
	}

	public void setIdTipPachet(Long IdTipPachet) {
		this.IdTipPachet = IdTipPachet;
	}

	public String getNumeMembru() {
		return this.NumeMembru;
	}

	public void setNumeMembru(String NumeMembru) {
		this.NumeMembru = NumeMembru;
	}

	public String getDenumirePachet() {
		return this.DenumirePachet;
	}

	public void setDenumirePachet(String DenumirePachet) {
		this.DenumirePachet = DenumirePachet;
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

	public Istoric_Abonati(Long id, Long IdMembru, Long IdTipPachet, String NumeMembru, String DenumirePachet, Date DataStart, Date DataSfarsit, Integer Valoare, String Observatii) {
		this.id = id;
		this.IdMembru = IdMembru;
		this.IdTipPachet = IdTipPachet;
		this.NumeMembru = NumeMembru;
		this.DenumirePachet = DenumirePachet;
		this.DataStart = DataStart;
		this.DataSfarsit = DataSfarsit;
		this.Valoare = Valoare;
		this.Observatii = Observatii;
	}
}
