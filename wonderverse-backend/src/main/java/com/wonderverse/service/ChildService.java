package com.wonderverse.service;

import com.wonderverse.dto.ChildDTO;
import com.wonderverse.entity.ChildProfile;
import com.wonderverse.entity.User;
import com.wonderverse.repository.ChildProfileRepository;
import com.wonderverse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildProfileRepository childProfileRepository;
    private final UserRepository userRepository;

    public ChildDTO.Response create(ChildDTO.Request req, String parentEmail) {
        User parent = userRepository.findByEmail(parentEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ChildProfile child = ChildProfile.builder()
                .parent(parent).name(req.getName()).age(req.getAge())
                .avatar(req.getAvatar()).screenTimeLimit(req.getScreenTimeLimit())
                .build();
        return toResponse(childProfileRepository.save(child));
    }

    public List<ChildDTO.Response> getByParent(String parentEmail) {
        User parent = userRepository.findByEmail(parentEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return childProfileRepository.findByParentId(parent.getId())
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ChildDTO.Response update(Long id, ChildDTO.Request req, String parentEmail) {
        ChildProfile child = childProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child not found"));
        child.setName(req.getName());
        child.setAge(req.getAge());
        child.setAvatar(req.getAvatar());
        child.setScreenTimeLimit(req.getScreenTimeLimit());
        return toResponse(childProfileRepository.save(child));
    }

    public void delete(Long id) {
        childProfileRepository.deleteById(id);
    }

    private ChildDTO.Response toResponse(ChildProfile c) {
        return ChildDTO.Response.builder()
                .id(c.getId()).name(c.getName()).age(c.getAge())
                .avatar(c.getAvatar()).screenTimeLimit(c.getScreenTimeLimit())
                .build();
    }
}