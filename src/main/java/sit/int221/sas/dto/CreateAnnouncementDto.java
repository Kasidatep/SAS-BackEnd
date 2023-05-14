package sit.int221.sas.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import sit.int221.sas.utils.AnnouncementDisplayEnum;
import sit.int221.sas.validators.ValidDate;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@ValidDate
public class CreateAnnouncementDto {
//   private Integer id;

    @NotNull
    @Size(min = 1, max = 200)
    private String announcementTitle;

    @NotNull
    @Size(min = 1, max = 10000)
    private String announcementDescription;

    @Future(message = "publishDate must be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime publishDate;

    @Future(message = "closeDate must be a future date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime closeDate;

    @NotNull( message = "announcementDisplay must be 'Y' or 'N'")
    private AnnouncementDisplayEnum announcementDisplay = AnnouncementDisplayEnum.N;

    @NotNull
    private Integer categoryId;

    public void setAnnouncementDisplay(String announcementDisplay) {
        if(Objects.equals(announcementDisplay, "N")){
            this.announcementDisplay = AnnouncementDisplayEnum.N;
        }else if(Objects.equals(announcementDisplay, "Y")){
            this.announcementDisplay = AnnouncementDisplayEnum.Y;
        } else{
            this.announcementDisplay = null;
        }
    }
}
