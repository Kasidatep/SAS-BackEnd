package sit.int221.sas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.sas.entities.Category;
import sit.int221.sas.utils.AnnouncementDisplayEnum;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnnouncementDto {
        private Integer id;
        private String announcementTitle;
        private String announcementDescription;
        private ZonedDateTime publishDate;
        private ZonedDateTime closeDate;
        private AnnouncementDisplayEnum announcementDisplay = AnnouncementDisplayEnum.N;
        private Integer categoryId;

}
