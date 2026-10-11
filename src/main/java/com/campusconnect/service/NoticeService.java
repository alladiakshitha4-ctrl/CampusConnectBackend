package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.NoticeRequestDTO;
import com.campusconnect.dto.NoticeResponseDTO;
import com.campusconnect.entity.Notice;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.NoticeRepo;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepo noticeRepo;

    public NoticeResponseDTO addNotice(NoticeRequestDTO dto) {

        validateDates(dto);

        Notice notice = new Notice();

        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        notice.setCategory(dto.getCategory());
        notice.setPublishedDate(dto.getPublishedDate());
        notice.setExpiryDate(dto.getExpiryDate());
        notice.setTargetAudience(dto.getTargetAudience());
        notice.setActive(dto.isActive());

        Notice savedNotice = noticeRepo.save(notice);

        return convertToResponseDTO(savedNotice);
    }

    public Page<NoticeResponseDTO> getNotices(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return noticeRepo.findAll(pageable)
                .map(this::convertToResponseDTO);
    }

    public Page<NoticeResponseDTO> searchNotices(
            String keyword,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("publishedDate").descending());

        return noticeRepo
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
                        keyword,
                        keyword,
                        pageable)
                .map(this::convertToResponseDTO);
    }

    public Page<NoticeResponseDTO> getNoticesByCategory(
            String category,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("publishedDate").descending());

        return noticeRepo
                .findByCategoryContainingIgnoreCase(
                        category,
                        pageable)
                .map(this::convertToResponseDTO);
    }

    public Page<NoticeResponseDTO> getActiveNotices(
            int page,
            int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("publishedDate").descending());

        return noticeRepo
                .findByActive(true, pageable)
                .map(this::convertToResponseDTO);
    }

    public NoticeResponseDTO getNoticeById(Long id) {

        Notice notice = noticeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notice with ID " + id + " not found"));

        return convertToResponseDTO(notice);
    }

    public NoticeResponseDTO updateNotice(
            Long id,
            NoticeRequestDTO dto) {

        Notice notice = noticeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notice with ID " + id + " not found"));

        validateDates(dto);

        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        notice.setCategory(dto.getCategory());
        notice.setPublishedDate(dto.getPublishedDate());
        notice.setExpiryDate(dto.getExpiryDate());
        notice.setTargetAudience(dto.getTargetAudience());
        notice.setActive(dto.isActive());

        Notice updatedNotice = noticeRepo.save(notice);

        return convertToResponseDTO(updatedNotice);
    }

    public void deleteNotice(Long id) {

        Notice notice = noticeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notice with ID " + id + " not found"));

        noticeRepo.delete(notice);
    }

    private NoticeResponseDTO convertToResponseDTO(Notice notice) {

        return new NoticeResponseDTO(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getCategory(),
                notice.getPublishedDate(),
                notice.getExpiryDate(),
                notice.getTargetAudience(),
                notice.isActive());
    }

    private void validateDates(NoticeRequestDTO dto) {

        if (dto.getExpiryDate().isBefore(dto.getPublishedDate())) {

            throw new IllegalArgumentException(
                    "Expiry date cannot be before published date");
        }
    }
}