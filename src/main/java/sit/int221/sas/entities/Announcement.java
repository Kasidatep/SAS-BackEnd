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
    @NotBlank(message = "announcementTitle is required")
    @Size(max = 200, message = "announcementTitle cannot be longer than 200 characters")
    private String announcementTitle;
    @Column(name = "announcementDescription", nullable = false)
    @NotBlank(message = "announcementDescription is required")
    @Size(max = 10000, message = "announcementDescription cannot be longer than 10,000 characters")
    private String announcementDescription;

    @Future(message = "publishDate must future")
    private ZonedDateTime publishDate;

    @Future(message = "closeDate must future")
    private ZonedDateTime closeDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "announcementDisplay")
    private AnnouncementDisplayEnum announcementDisplay;

    @JsonIgnore
    @ManyToOne
    @NotNull(message = "Category can not be null")
    @JoinColumn(name = "categoryId")
    private Category category;
}
