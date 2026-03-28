package com.wonderverse.repository;

import com.wonderverse.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findByChildId(Long childId);

    @Query("SELECT COALESCE(SUM(r.points), 0) FROM Reward r WHERE r.child.id = :childId")
    Integer sumPointsByChildId(Long childId);
}