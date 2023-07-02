package com.radu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_member")
    private Long idMember;

    @NotNull
    @Column(name = "id_package")
    private Long idPackage;

    @Column(name = "member_familyname", nullable = false)
    private String memberFamilyName;

    @Column(name = "member_personalname")
    private String memberPersonalName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_start")
    private Date dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_end")
    private Date dateEnd;

	@Column(name = "value")
    private Integer value;

	@Column(name = "observations")
    private String observations;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMember() {
        return idMember;
    }

    public void setIdMember(Long idMember) {
        this.idMember = idMember;
    }

    public Long getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(Long idPackage) {
        this.idPackage = idPackage;
    }

    public String getMemberFamilyName() {
        return memberFamilyName;
    }

    public void setMemberFamilyName(String memberFamilyName) {
        this.memberFamilyName = memberFamilyName;
    }

    public String getMemberPersonalName() {
        return memberPersonalName;
    }

    public void setMemberPersonalName(String memberFirstName) {
        this.memberPersonalName = memberFirstName;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
