package sit.int221.sas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import sit.int221.sas.utils.AnnouncementDisplayEnum;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="announcement")
public class Announcement {
    @Id
    @Column(name = "announcementId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "announcementTitle", nullable = false)
    private String announcementTitle;

    @Column(name = "announcementDescription", nullable = false)
    private String announcementDescription;

    private ZonedDateTime publishDate;

    private ZonedDateTime closeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "announcementDisplay")
    private AnnouncementDisplayEnum announcementDisplay;

    @Column(name = "announcementView")
    private Integer view;

    @JsonIgnore
    @ManyToOne
    @NotNull
    @JoinColumn(name = "categoryId")
    private Category category;
}
