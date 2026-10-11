package com.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.dto.NoticeRequestDTO;
import com.campusconnect.dto.NoticeResponseDTO;
import com.campusconnect.service.NoticeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notices")
@CrossOrigin(origins = "http://localhost:5173")
public class NoticeController {

    @Autowired
    private NoticeService service;

    @PostMapping
    public NoticeResponseDTO addNotice(
            @Valid @RequestBody NoticeRequestDTO noticeDTO) {

        return service.addNotice(noticeDTO);
    }

    @GetMapping
    public Page<NoticeResponseDTO> getNotices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        return service.getNotices(
                page,
                size,
                sortBy,
                direction);
    }

    @GetMapping("/search")
    public Page<NoticeResponseDTO> searchNotices(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.searchNotices(
                keyword,
                page,
                size);
    }

    @GetMapping("/category/{category}")
    public Page<NoticeResponseDTO> getNoticesByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getNoticesByCategory(
                category,
                page,
                size);
    }

    @GetMapping("/active")
    public Page<NoticeResponseDTO> getActiveNotices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getActiveNotices(page, size);
    }

    @GetMapping("/{id}")
    public NoticeResponseDTO getNoticeById(
            @PathVariable Long id) {

        return service.getNoticeById(id);
    }

    @PutMapping("/{id}")
    public NoticeResponseDTO updateNotice(
            @PathVariable Long id,
            @Valid @RequestBody NoticeRequestDTO noticeDTO) {

        return service.updateNotice(id, noticeDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteNotice(
            @PathVariable Long id) {

        service.deleteNotice(id);

        return "Notice deleted successfully";
    }
}