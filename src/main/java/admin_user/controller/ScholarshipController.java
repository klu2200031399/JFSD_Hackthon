package admin_user.controller;

import admin_user.model.Scholarship;
import admin_user.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    // Show the form to add a new scholarship
    @GetMapping("/add-scholarship")
    public String showAddScholarshipForm(Model model) {
        model.addAttribute("scholarship", new Scholarship());
        return "add-scholarship"; // Page where admin can add scholarship details
    }

    // Save the new scholarship into the database
    @PostMapping("/add-scholarship")
    public String saveScholarship(@ModelAttribute("scholarship") Scholarship scholarship) {
        scholarshipService.save(scholarship);
        return "redirect:/view-scholarships"; // Redirect to view all scholarships
    }

    // Display all scholarships
    @GetMapping("/view-scholarships")
    public String viewScholarships(Model model) {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        model.addAttribute("scholarships", scholarships);
        return "view-scholarships"; // Displays the list of scholarships
    }

    // Show the form to edit a scholarship
    @GetMapping("/edit-scholarship/{id}")
    public String editScholarship(@PathVariable Long id, Model model) {
        Scholarship scholarship = scholarshipService.findById(id);
        if (scholarship == null) {
            throw new RuntimeException("Scholarship not found for ID: " + id);
        }
        model.addAttribute("scholarship", scholarship);
        return "edit-scholarship"; // Page to edit scholarship details
    }

    // Update the scholarship details
    @PostMapping("/edit-scholarship")
    public String updateScholarship(@ModelAttribute("scholarship") Scholarship scholarship) {
        scholarshipService.save(scholarship); // Save updated scholarship details
        return "redirect:/view-scholarships"; // Redirect to view updated list
    }

    // Delete a scholarship
    @GetMapping("/delete-scholarship/{id}")
    public String deleteScholarship(@PathVariable Long id) {
        scholarshipService.deleteById(id); // Delete scholarship by ID
        return "redirect:/view-scholarships"; // Redirect to view all scholarships
    }
}
