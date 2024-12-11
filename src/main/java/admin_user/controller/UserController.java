package admin_user.controller;

import java.security.Principal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import admin_user.dto.UserDto;
import admin_user.model.Scholarship;
import admin_user.model.ScholarshipApplication;
import admin_user.model.User;
import admin_user.service.EmailService;
import admin_user.service.ScholarshipApplicationService;
import admin_user.service.ScholarshipService;
import admin_user.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    private UserService userService;
    @Autowired
    private ScholarshipService scholarshipService;
    @Autowired
    private ScholarshipApplicationService scholarshipApplicationService;

    @GetMapping("/view-applications")
    public String viewApplications(Model model) {
        model.addAttribute("applications", scholarshipApplicationService.getAllApplications());
        return "view-applications";  // Render the view-applications.html page
    }

    // Approve scholarship application
    @GetMapping("/approve-application/{id}")
    public String approveApplication(@PathVariable("id") Long id) {
        scholarshipApplicationService.updateApplicationStatus(id, "APPROVED");
        return "redirect:/view-applications";  // Redirect to view applications page after approval
    }

    // Reject scholarship application
    @GetMapping("/reject-application/{id}")
    public String rejectApplication(@PathVariable("id") Long id) {
        scholarshipApplicationService.updateApplicationStatus(id, "REJECTED");
        return "redirect:/view-applications";  // Redirect to view applications page after rejection
    }
    @GetMapping("/apply-scholarship")
    public String showApplicationForm(Model model) {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships(); // Fetch scholarships from the database
        model.addAttribute("scholarships", scholarships);
        model.addAttribute("scholarshipApplication", new ScholarshipApplication());
        return "apply-scholarship"; // Return the apply-scholarship.html view
    }

    // Handle form submission for scholarship application
    @PostMapping("/apply-scholarship")
    public String submitApplication(ScholarshipApplication scholarshipApplication) {
        scholarshipApplicationService.saveScholarshipApplication(scholarshipApplication); // Save the application to the database
        return "redirect:/user-page"; 
    }
    // Home page (root URL)
    @GetMapping("/")
    public String homePage() {
        return "home"; 
    }

    // Registration page for new users (e.g., students or admins)
    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
        return "register";  // page to register a user (student/admin)
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
    	userDto.setRole("USER");
        userService.save(userDto);
        model.addAttribute("message", "Registered Successfully!");
        return "register";  // after successful registration, redirect back to registration page
    }

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    
    @GetMapping("user-page")
    public String userPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "user";  
    }

    // Admin dashboard page (for admins)
    @GetMapping("admin-page")
    public String adminPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin";  // admin dashboard page
    }

    @GetMapping("/add-student")
    public String showAddStudentForm(@ModelAttribute("user") UserDto userDto) {
        return "add-student"; 
    }
    @PostMapping("/add-student")
    public String saveStudent(@ModelAttribute("user") UserDto userDto, Model model) {
        userDto.setRole("ADMIN");
        User savedUser = userService.save(userDto);

        // Notify success (without email)
        model.addAttribute("message", "Student Registered Successfully!");
        return "redirect:/admin-page";
    }

    @GetMapping("/viewusers")
    public String viewAllUsers(Model model) {
        List<User> users = userService.getUsersByRole("USER");
        model.addAttribute("users", users);  // Ensure 'users' attribute is correctly added to the model
        return "viewusers";  // Ensure the template name matches the file name exactly
    }

   
    @GetMapping("/view")
    public String view(Model model) {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        model.addAttribute("scholarships", scholarships);
        return "view";
    }
  

}
