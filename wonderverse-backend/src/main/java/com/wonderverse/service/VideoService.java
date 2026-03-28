package com.wonderverse.service;

import com.wonderverse.dto.VideoDTO;
import com.wonderverse.entity.User;
import com.wonderverse.entity.Video;
import com.wonderverse.repository.UserRepository;
import com.wonderverse.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public List<VideoDTO.Response> getAll() {
        return videoRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public VideoDTO.Response getById(Long id) {
        return toResponse(videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found")));
    }

    public VideoDTO.Response upload(VideoDTO.Request req, String uploaderEmail) {
        User uploader = userRepository.findByEmail(uploaderEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Video video = Video.builder()
                .title(req.getTitle()).description(req.getDescription())
                .ageGroup(req.getAgeGroup()).category(req.getCategory())
                .videoUrl(req.getVideoUrl()).thumbnailUrl(req.getThumbnailUrl())
                .duration(req.getDuration()).uploadedBy(uploader).build();
        return toResponse(videoRepository.save(video));
    }

    public void delete(Long id) {
        videoRepository.deleteById(id);
    }

    private VideoDTO.Response toResponse(Video v) {
        return VideoDTO.Response.builder()
                .id(v.getId()).title(v.getTitle()).description(v.getDescription())
                .ageGroup(v.getAgeGroup()).category(v.getCategory())
                .videoUrl(v.getVideoUrl()).thumbnailUrl(v.getThumbnailUrl())
                .duration(v.getDuration())
                .createdAt(v.getCreatedAt() != null ? v.getCreatedAt().toString() : null)
                .build();
    }
}