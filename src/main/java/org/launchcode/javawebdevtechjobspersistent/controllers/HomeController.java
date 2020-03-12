package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;


    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }else{

            Optional<Employer> result = employerRepository.findById(employerId);

            if(result.isEmpty()){
                model.addAttribute("title", "Add Job");
                return "add";
            }else{
                Employer employer = result.get();
                newJob.setEmployer(employer);
            }
        }
        jobRepository.save(newJob);

        //jobdata call findby to get the jobID
        //Integer jobID = newJob.getId();
        //model.addAttribute("jobs", jobRepository.findAll());

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob( Model model, @PathVariable int jobId) {



        Optional<Job> result = jobRepository.findById(jobId);

        if(result.isEmpty()) {
            return "redirect:";
        }else{
            Job job = result.get();
            model.addAttribute("job", job);
        }
        return "view";
    }
}
