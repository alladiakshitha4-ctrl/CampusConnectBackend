package com.campusconnect.dto;

public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(
            Long id,
            String name,
            String email,
            String phone,
            String department) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}