package sit.int221.sas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sit.int221.sas.entities.Category;
import sit.int221.sas.utils.AnnouncementDisplayEnum;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllAnnouncementDto {
        @Id
        private Integer id;
        private String announcementTitle;
        private String publishDate;
        private String closeDate;
        private AnnouncementDisplayEnum announcementDisplay;
        @JsonIgnore
        private Category category;
        public String getAnnouncementCategory() {
            return category.getCategoryName();
        }

}
