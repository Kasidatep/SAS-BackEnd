package sit.int221.sas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sit.int221.sas.entities.Category;
import sit.int221.sas.utils.AnnouncementDisplay;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllAnnouncement {
        @Id
        private Integer id;
        private String announcementTitle;
        private String publishDate;
        private String closeDate;

        private AnnouncementDisplay announcementDisplay;
        @ManyToOne
        @JsonIgnore
        @JoinColumn(name = "categoryId")
        private Category category;

        public String getAnnouncementCategory() {
            return category.getCategoryName();
        }

}
