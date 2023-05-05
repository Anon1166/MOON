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

public class EditAnnDto {
    private Integer id;
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    @Enumerated(EnumType.STRING)
    private Enum announcementDisplay;

    @JsonIgnore
    private Integer categoriesCategoryId ;
    public Integer getCategoryId(){return categoriesCategoryId;}
    @JsonIgnore
    private String categoriesAnnouncementCategory;
    public String getAnnouncementCategory(){return categoriesAnnouncementCategory;}

}
