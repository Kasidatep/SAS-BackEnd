package sit.int221.sas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import sit.int221.sas.utils.AnnouncementDisplayEnum;
import sit.int221.sas.validators.PublishAndCloseDate;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CreateAnnouncementDto {
                private Integer id;

                @NotNull(message = "announcementTitle is required")
                @Size(min = 1, max = 200)
                private String announcementTitle;

                @NotNull(message = "announcementDescription is required")
                @Size(min = 1, max = 10000)
                private String announcementDescription;

                @Future(message = "publishDate must be in the future")
               // @PublishAndCloseDate
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                private ZonedDateTime publishDate;

                @Future(message = "closeDate must be in the future")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                private ZonedDateTime closeDate;

                private AnnouncementDisplayEnum announcementDisplay = AnnouncementDisplayEnum.N;

                @NotNull
                private Integer categoryId;

}
