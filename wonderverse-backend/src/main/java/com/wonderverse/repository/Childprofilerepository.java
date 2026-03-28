package com.wonderverse.repository;

import com.wonderverse.entity.ChildProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChildProfileRepository extends JpaRepository<ChildProfile, Long> {
    List<ChildProfile> findByParentId(Long parentId);
}