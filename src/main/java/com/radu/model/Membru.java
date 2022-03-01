package com.radu.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Membru {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nume;
	private String prenume;
	private String email;
	private String telefon;
	private Date data;
	private String denumirepachet;
	private Date datastart;
	private Date datasfarsit;
	
/*	
	public Membru() {
	}

    public Membru(Long id, String nume, String prenume, String email, String telefon, Date data) {
    	this.id=id;
    	this.nume=nume;
    	this.prenume=prenume;
		this.email=email;
		this.telefon=telefon;
    	this.data=data;		
	}
*/
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getEmail() {
		return email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getNume() {
		return nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getDenumirepachet() {
		return denumirepachet;
	}
	public void setDenumirepachet(String denumirepachet) {
		this.denumirepachet = denumirepachet;
	}

	public Date getDatastart() {
		return datastart;
	}
	public void setDatastart(Date datastart) {
		this.datastart = datastart;
	}

	public Date getDatasfarsit() {
		return datasfarsit;
	}
	public void setDatasfarsit(Date datasfarsit) {
		this.datasfarsit = datasfarsit;
	}

	@Override
    public String toString() {
        return "Membru {" + "id=" + this.id + ", name='" + this.nume + this.prenume + this.email+ this.telefon+ '\'' + ", role='" + this.data + '\'' + '}';
    }

	@Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Membru))
            return false;
        Membru membru = (Membru) o;
        return Objects.equals(this.id, membru.id) && Objects.equals(this.nume, membru.prenume) && Objects.equals(this.nume, membru.prenume)
                && Objects.equals(this.data, membru.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nume,this.prenume, this.data);
    }

}
