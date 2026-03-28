package com.wonderverse.controller;

import com.wonderverse.dto.VideoDTO;
import com.wonderverse.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/api/videos")
    public ResponseEntity<List<VideoDTO.Response>> getAll() {
        return ResponseEntity.ok(videoService.getAll());
    }

    @GetMapping("/api/videos/{id}")
    public ResponseEntity<VideoDTO.Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getById(id));
    }

    @PostMapping("/api/admin/videos")
    public ResponseEntity<VideoDTO.Response> upload(
            @Valid @RequestBody VideoDTO.Request req, Authentication auth) {
        return ResponseEntity.ok(videoService.upload(req, auth.getName()));
    }

    @DeleteMapping("/api/admin/videos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        videoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}