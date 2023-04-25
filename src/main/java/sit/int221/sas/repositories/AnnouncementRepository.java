package sit.int221.sas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sit.int221.sas.entities.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
}
