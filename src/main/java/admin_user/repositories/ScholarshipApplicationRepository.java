package admin_user.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import admin_user.model.ScholarshipApplication;


public interface ScholarshipApplicationRepository extends JpaRepository<ScholarshipApplication, Long> {
	
}
