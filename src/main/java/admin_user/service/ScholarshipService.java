package admin_user.service;

import admin_user.model.Scholarship;
import admin_user.repositories.ScholarshipRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    // Save a scholarship
    public void save(Scholarship scholarship) {
        scholarshipRepository.save(scholarship);
    }

    // Retrieve all scholarships
    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    // Retrieve scholarship by ID
    public Scholarship findById(Long id) {
        return scholarshipRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Scholarship not found"));
    }

    // Delete scholarship by ID
    public void deleteById(Long id) {
        Optional<Scholarship> scholarship = scholarshipRepository.findById(id);
        
        if (scholarship.isPresent()) {
            scholarshipRepository.delete(scholarship.get());
        } else {
            throw new EntityNotFoundException("Scholarship with ID " + id + " not found");
        }
    }

    // Implemented getScholarshipById to return the scholarship
    public Scholarship getScholarshipById(Long id) {
        return scholarshipRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Scholarship not found with ID: " + id));
    }
}
