package com.example.WebApp.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_id;

    @Column(nullable = false, length = 255)
    private String department_name;

    @Column(nullable = false)
    private LocalDateTime create_date;

    @Column(nullable = false)
    private LocalDateTime update_date;

    // 登録時に自動で日時を入れる
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.create_date = now;
        this.update_date = now;
    }

    // 更新時に日時を更新
    @PreUpdate
    public void onUpdate() {
        this.update_date = LocalDateTime.now();
    }

    // ===== Getter / Setter =====

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
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
}
