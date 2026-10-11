package com.campusconnect.dto;

import java.time.LocalDateTime;

public class NoticeResponseDTO {

    private Long id;

    private String title;

    private String content;

    private String category;

    private LocalDateTime publishedDate;

    private LocalDateTime expiryDate;

    private String targetAudience;

    private boolean active;

    public NoticeResponseDTO() {
    }

    public NoticeResponseDTO(Long id,
                             String title,
                             String content,
                             String category,
                             LocalDateTime publishedDate,
                             LocalDateTime expiryDate,
                             String targetAudience,
                             boolean active) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.publishedDate = publishedDate;
        this.expiryDate = expiryDate;
        this.targetAudience = targetAudience;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}