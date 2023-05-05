package sit.int221.sas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sit.int221.sas.entities.Category;
import sit.int221.sas.utils.AnnouncementDisplayEnum;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnnouncementReturnDto {
        private Integer id;
        private String announcementTitle;
        private String announcementDescription;
        private ZonedDateTime publishDate;
        private ZonedDateTime closeDate;
        private AnnouncementDisplayEnum announcementDisplay;
        @JsonIgnore
        private Category category;
        public String getAnnouncementCategory() {
            return category.getCategoryName();
        }
        public Integer getCategoryId() {
                return category.getId();
        }
}
