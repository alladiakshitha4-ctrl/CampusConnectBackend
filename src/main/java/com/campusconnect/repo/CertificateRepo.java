package com.campusconnect.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.entity.Certificate;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate, Long> {

    Optional<Certificate> findByCertificateId(String certificateId);

    boolean existsByCertificateId(String certificateId);
}