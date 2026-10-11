package com.campusconnect.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.entity.Certificate;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.CertificateRepo;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepo certificateRepo;

    public Certificate createCertificate(Certificate certificate) {

        String certificateId;

        do {
            certificateId = "CC-" + UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();
        } while (certificateRepo.existsByCertificateId(certificateId));

        certificate.setCertificateId(certificateId);

        if (certificate.getIssueDate() == null) {
            certificate.setIssueDate(LocalDate.now());
        }

        certificate.setStatus("VALID");

        return certificateRepo.save(certificate);
    }

    public List<Certificate> getAllCertificates() {
        return certificateRepo.findAll();
    }

    public Certificate getCertificateById(Long id) {

        return certificateRepo.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Certificate with ID " + id + " not found"));
    }

    public Certificate verifyCertificate(String certificateId) {

        return certificateRepo.findByCertificateId(certificateId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Certificate with ID " + certificateId
                        + " not found"));
    }

    public void deleteCertificate(Long id) {

        Certificate certificate = getCertificateById(id);

        certificateRepo.delete(certificate);
    }
}