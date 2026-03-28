package com.wonderverse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class VideoDTO {

    @Data
    public static class Request {
        @NotBlank private String title;
        private String description;
        private String ageGroup;
        private String category;
        @NotBlank private String videoUrl;
        private String thumbnailUrl;
        private Integer duration;
    }

    @Data
    @lombok.Builder
    @lombok.AllArgsConstructor
    @lombok.NoArgsConstructor
    public static class Response {
        private Long id;
        private String title;
        private String description;
        private String ageGroup;
        private String category;
        private String videoUrl;
        private String thumbnailUrl;
        private Integer duration;
        private String createdAt;
    }
}