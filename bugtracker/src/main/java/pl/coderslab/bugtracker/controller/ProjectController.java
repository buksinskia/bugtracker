package pl.coderslab.bugtracker.controller;

import jakarta.validation.Valid;
import pl.coderslab.bugtracker.model.Project;
import pl.coderslab.bugtracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String listProjects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "project/list";
    }

    @GetMapping("/new")
    public String showProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "project/form";
    }

    @PostMapping
    public String saveProject(@Valid Project project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "project/form";
        }
        projectRepository.save(project);
        return "redirect:/projects";
    }
}
