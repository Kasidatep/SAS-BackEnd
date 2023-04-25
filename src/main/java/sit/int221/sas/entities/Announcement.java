package sit.int221.sas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sit.int221.sas.utils.AnnouncementDisplay;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="announcement")
public class Announcement {
    @Id
    @Column(name = "announcementId")
    private Integer id;
    private String announcementTitle;
    private String announcementDescription;
    private String publishDate;
    private String closeDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "announcementDisplay")
    private AnnouncementDisplay announcementDisplay;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
