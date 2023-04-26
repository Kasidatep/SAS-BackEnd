package sit.int221.sas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class AnnouncementDetail {
        @Id
        private Integer id;
        private String announcementTitle;
        private String announcementDescription;
        private String publishDate;
        private String closeDate;
        private AnnouncementDisplay announcementDisplay;
        @JsonIgnore
        private Category category;
        public String getAnnouncementCategory() {
            return category.getCategoryName();
        }

}
