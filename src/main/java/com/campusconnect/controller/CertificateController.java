package com.campusconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.entity.Certificate;
import com.campusconnect.service.CertificateService;

@RestController
@RequestMapping("/certificates")
@CrossOrigin(origins = "http://localhost:5173")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping
    public ResponseEntity<Certificate> createCertificate(
            @RequestBody Certificate certificate) {

        return ResponseEntity.ok(
                certificateService.createCertificate(certificate));
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getAllCertificates() {

        return ResponseEntity.ok(
                certificateService.getAllCertificates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                certificateService.getCertificateById(id));
    }

    @GetMapping("/verify/{certificateId}")
    public ResponseEntity<Certificate> verifyCertificate(
            @PathVariable String certificateId) {

        return ResponseEntity.ok(
                certificateService.verifyCertificate(certificateId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCertificate(
            @PathVariable Long id) {

        certificateService.deleteCertificate(id);

        return ResponseEntity.ok(
                "Certificate deleted successfully");
    }
}