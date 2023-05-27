package sit.int221.sas.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.validation.annotation.Validated;


import sit.int221.sas.utils.AnnouncementDisplayEnum;
import sit.int221.sas.validators.CategoryIdExists;
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
    @NotBlank
    @Size(min = 1, max = 200)
    private String announcementTitle;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 10000)
    private String announcementDescription;

    @FutureOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime publishDate;

    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime closeDate;

    @NotNull( message = "must be either 'Y' or 'N'")
    private AnnouncementDisplayEnum announcementDisplay = AnnouncementDisplayEnum.N;

    @NotNull
    @CategoryIdExists
    private Integer categoryId;

    public void setAnnouncementDisplay(String announcementDisplay) {
        if(Objects.equals(announcementDisplay, "N")){
            this.announcementDisplay = AnnouncementDisplayEnum.N;
        }else if(Objects.equals(announcementDisplay, "Y")){
            this.announcementDisplay = AnnouncementDisplayEnum.Y;
        }else if(announcementDisplay==null){
            this.announcementDisplay = AnnouncementDisplayEnum.N;
    } else{
            this.announcementDisplay = null;
        }
    }
}
