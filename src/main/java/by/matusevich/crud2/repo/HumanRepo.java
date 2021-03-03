package by.matusevich.crud2.repo;

import by.matusevich.crud2.model.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepo extends JpaRepository<Human, Long> {
}
