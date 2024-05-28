package pl.coderslab.bugtracker.repository;

import pl.coderslab.bugtracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
