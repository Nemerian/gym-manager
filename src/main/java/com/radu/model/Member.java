package com.radu.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Column(name = "personal_name", nullable = false)
    private String personalName;

    @Column(name = "email", nullable = false)
    private String email;

	@Column(name = "phone")
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_register")
	private Date dateRegister;

    @Column(name = "id_subscription")
    private Long idSubscription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	@Column(name = "date_start")
    private Date dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
	@Column(name = "date_end")
    private Date dateEnd;

	@Column(name = "observations")
    private String observations;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Long getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(Long idSubscription) {
        this.idSubscription = idSubscription;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", familyName='" + familyName + '\'' +
                ", personalName='" + personalName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateRegister=" + dateRegister +
                ", packageName='" + idSubscription + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", observations='" + observations + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(familyName, member.familyName) &&
                Objects.equals(personalName, member.personalName) &&
                Objects.equals(email, member.email) &&
                Objects.equals(phone, member.phone) &&
                Objects.equals(dateRegister, member.dateRegister) &&
                Objects.equals(idSubscription, member.idSubscription) &&
                Objects.equals(dateStart, member.dateStart) &&
                Objects.equals(dateEnd, member.dateEnd) &&
                Objects.equals(observations, member.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, familyName, personalName, email, phone, dateRegister, idSubscription, dateStart, dateEnd, observations);
    }
}
