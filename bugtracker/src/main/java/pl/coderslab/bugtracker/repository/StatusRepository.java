package pl.coderslab.bugtracker.repository;
import pl.coderslab.bugtracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRepository extends JpaRepository<Status, Long> {
}
