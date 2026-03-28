package com.wonderverse.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class ChildDTO {

    @Data
    public static class Request {
        @NotBlank private String name;
        @NotNull @Min(3) @Max(15) private Integer age;
        private String avatar;
        private Integer screenTimeLimit = 2;
    }

    @Data
    @lombok.Builder
    @lombok.AllArgsConstructor
    @lombok.NoArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private Integer age;
        private String avatar;
        private Integer screenTimeLimit;
    }
}