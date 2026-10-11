package com.campusconnect.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NoticeRequestDTO {

    @NotBlank(message = "Notice title is required")
    @Size(max = 200, message = "Notice title cannot exceed 200 characters")
    private String title;

    @NotBlank(message = "Notice content is required")
    private String content;

    @NotBlank(message = "Notice category is required")
    private String category;

    @NotNull(message = "Published date is required")
    private LocalDateTime publishedDate;

    @NotNull(message = "Expiry date is required")
    private LocalDateTime expiryDate;

    @NotBlank(message = "Target audience is required")
    private String targetAudience;

    private boolean active;

    public NoticeRequestDTO() {
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