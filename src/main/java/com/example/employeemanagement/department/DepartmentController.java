package com.example.employeemanagement.department;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepository repository;

    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Department> getAll() { return repository.findAll(); }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        Department saved = repository.save(department);
        return ResponseEntity.created(URI.create("/api/departments/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found: " + id));
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department) {
        Department existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found: " + id));
        existing.setName(department.getName());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Department existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found: " + id));
        repository.delete(existing);
        return ResponseEntity.noContent().build();
    }
}
