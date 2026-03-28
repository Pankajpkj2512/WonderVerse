package com.wonderverse.repository;

import com.wonderverse.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByAgeGroup(String ageGroup);
    List<Video> findByCategory(String category);
    List<Video> findByAgeGroupIn(List<String> ageGroups);
}