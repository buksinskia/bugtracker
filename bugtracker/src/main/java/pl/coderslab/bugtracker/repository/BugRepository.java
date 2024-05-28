package pl.coderslab.bugtracker.repository;

import pl.coderslab.bugtracker.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.bugtracker.model.Bug;

public interface BugRepository extends JpaRepository<Bug, Long> {

}
