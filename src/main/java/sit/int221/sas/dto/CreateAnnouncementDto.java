package sit.int221.sas.dto;

import jakarta.validation.constraints.*;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime publishDate;

    @Future(message = "must be a future date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime closeDate;

    @AssertTrue(message = "closeDate must be later than publishDate")
    public boolean isCloseDateValid() {
        if (closeDate == null || publishDate == null) {
            return true; // Skip validation if either date is null
        }
        return closeDate.isAfter(publishDate);
    }

    private AnnouncementDisplayEnum announcementDisplay = AnnouncementDisplayEnum.N;

    @NotNull
    private Integer categoryId;

}
