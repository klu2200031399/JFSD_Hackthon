package admin_user.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin_user.model.ScholarshipApplication;
import admin_user.repositories.ScholarshipApplicationRepository;


@Service
public class ScholarshipApplicationService {

    @Autowired
    private ScholarshipApplicationRepository scholarshipApplicationRepository;

    // Save scholarship application to the database
    public void saveScholarshipApplication(ScholarshipApplication scholarshipApplication) {
        scholarshipApplicationRepository.save(scholarshipApplication);
    }
    public List<ScholarshipApplication> getAllApplications() {
        return scholarshipApplicationRepository.findAll();
    }


    public void updateApplicationStatus(Long id, String status) {
        ScholarshipApplication application = scholarshipApplicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        scholarshipApplicationRepository.save(application);
    }
    

}
