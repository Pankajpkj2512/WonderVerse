package com.wonderverse.controller;

import com.wonderverse.dto.ChildDTO;
import com.wonderverse.service.ChildService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/parent")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping("/child")
    public ResponseEntity<ChildDTO.Response> create(
            @Valid @RequestBody ChildDTO.Request req, Authentication auth) {
        return ResponseEntity.ok(childService.create(req, auth.getName()));
    }

    @GetMapping("/children")
    public ResponseEntity<List<ChildDTO.Response>> getAll(Authentication auth) {
        return ResponseEntity.ok(childService.getByParent(auth.getName()));
    }

    @PutMapping("/child/{id}")
    public ResponseEntity<ChildDTO.Response> update(
            @PathVariable Long id, @Valid @RequestBody ChildDTO.Request req, Authentication auth) {
        return ResponseEntity.ok(childService.update(id, req, auth.getName()));
    }

    @DeleteMapping("/child/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        childService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
