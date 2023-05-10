package sit.int221.sas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sit.int221.sas.entities.Announcement;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    List<Announcement> findAllByCategory(String category);

    @Query("SELECT e FROM Announcement e WHERE "
            + "e.announcementDisplay = 'Y' AND ("
            + "(e.publishDate IS NULL AND e.closeDate IS NULL) "
            + "OR (e.publishDate IS NULL AND e.closeDate >= :now) "
            + "OR (e.publishDate <= :now AND e.closeDate IS NULL) "
            + "OR (e.publishDate <= :now AND e.closeDate >= :now)) "
            + "ORDER BY e.id DESC ")
    List<Announcement> findActive(ZonedDateTime now);

    @Query("SELECT e FROM Announcement e WHERE "
            + "e.announcementDisplay = 'Y' AND e.closeDate <= :now "
            + "ORDER BY e.id DESC ")
    List<Announcement> findClosed(ZonedDateTime now);
    @Query("SELECT e FROM Announcement e WHERE "
            + "e.announcementDisplay = 'Y' AND ("
            + "(e.publishDate IS NULL AND e.closeDate IS NULL) "
            + "OR (e.publishDate IS NULL AND e.closeDate >= :now) "
            + "OR (e.publishDate <= :now AND e.closeDate IS NULL) "
            + "OR (e.publishDate <= :now AND e.closeDate >= :now))")
    Page<Announcement> findActive(ZonedDateTime now, Pageable pageable);

    @Query("SELECT e FROM Announcement e WHERE "
            + "e.announcementDisplay = 'Y' AND e.closeDate <= :now")
    Page<Announcement> findClosed(ZonedDateTime now, Pageable pageable);

    @Query("SELECT e FROM Announcement e WHERE e.category.id = :categoryId AND "
            + "e.announcementDisplay = 'Y' AND ("
            + "(e.publishDate IS NULL AND e.closeDate IS NULL) "
            + "OR (e.publishDate IS NULL AND e.closeDate >= :now) "
            + "OR (e.publishDate <= :now AND e.closeDate IS NULL) "
            + "OR (e.publishDate <= :now AND e.closeDate >= :now))")
    Page<Announcement> findActive(ZonedDateTime now,Integer categoryId, Pageable pageable);

    @Query("SELECT e FROM Announcement e WHERE  e.category.id = :categoryId AND "
            + "e.announcementDisplay = 'Y' AND e.closeDate <= :now")
    Page<Announcement> findClosed(ZonedDateTime now,Integer categoryId, Pageable pageable);

}
