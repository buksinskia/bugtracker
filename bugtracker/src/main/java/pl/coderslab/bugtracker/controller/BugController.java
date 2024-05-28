package pl.coderslab.bugtracker.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.bugtracker.model.Bug;
import pl.coderslab.bugtracker.repository.BugRepository;
import pl.coderslab.bugtracker.repository.ProjectRepository;
import pl.coderslab.bugtracker.repository.StatusRepository;
import pl.coderslab.bugtracker.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/bugs")
public class BugController {

    private final BugRepository bugRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public BugController(BugRepository bugRepository, ProjectRepository projectRepository,
                         UserRepository userRepository, StatusRepository statusRepository) {
        this.bugRepository = bugRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public String listBugs(Model model) {
        List<Bug> bugs = bugRepository.findAll();
        model.addAttribute("bugs", bugs);
        return "bug/list";
    }

    @GetMapping("/new")
    public String showBugForm(Model model) {
        model.addAttribute("bug", new Bug());
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("statuses", statusRepository.findAll());
        return "bug/form";
    }

    @PostMapping
    public String saveBug(@Valid @ModelAttribute("bug") Bug bug, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("projects", projectRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("statuses", statusRepository.findAll());
            return "bug/form";
        }
        bugRepository.save(bug);
        return "redirect:/bugs";
    }

    @GetMapping("/edit/{id}")
    public String showEditBugForm(@PathVariable("id") Long id, Model model) {
        Bug bug = bugRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bug id: " + id));
        model.addAttribute("bug", bug);
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("statuses", statusRepository.findAll());

        return "bug/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBug(@PathVariable("id") Long id, @Valid @ModelAttribute("bug") Bug bug, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("projects", projectRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("statuses", statusRepository.findAll());
            return "bug/edit";
        }
        bug.setId(id);
        bugRepository.save(bug);
        return "redirect:/bugs";
    }

    @GetMapping("/delete/{id}")
    public String deleteBug(@PathVariable("id") Long id) {
        bugRepository.deleteById(id);
        return "redirect:/bugs";
    }
}
