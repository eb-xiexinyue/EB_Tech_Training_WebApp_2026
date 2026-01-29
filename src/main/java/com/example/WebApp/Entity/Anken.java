package com.example.WebApp.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "anken")
public class Anken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long anken_id;

    private String anken_name;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime create_date;

    @Column(nullable = false)
    private LocalDateTime update_date;
    
    public Long getAnken_id() {
		return anken_id;
	}

	public void setAnken_id(Long anken_id) {
		this.anken_id = anken_id;
	}

	public String getAnken_name() {
		return anken_name;
	}

	public void setAnken_name(String anken_name) {
		this.anken_name = anken_name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public LocalDateTime getCreate_date() {
		return create_date;
	}

	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}

	public LocalDateTime getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}

	@PrePersist
    public void onCreate() {
    	LocalDateTime now = LocalDateTime.now();
    	this.create_date = now;
    	this.update_date = now;
    }
    
    @PreUpdate
    public void onUpdate() {
    	LocalDateTime now = LocalDateTime.now();
    	this.update_date = now;
    }
}
