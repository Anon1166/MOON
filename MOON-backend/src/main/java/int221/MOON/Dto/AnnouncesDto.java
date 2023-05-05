package int221.MOON.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import int221.MOON.Enum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter

public class AnnouncesDto {
    private Integer id;
    private String announcementTitle;
    @JsonIgnore
    private String categoriesAnnouncementCategory;
    public String getAnnouncementCategory(){return categoriesAnnouncementCategory;}
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    @Enumerated(EnumType.STRING)
    private Enum announcementDisplay;

}
