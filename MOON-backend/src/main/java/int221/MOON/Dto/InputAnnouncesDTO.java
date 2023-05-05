package int221.MOON.Dto;

import int221.MOON.Enum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
public class InputAnnouncesDTO {


        private Integer id;
        private String announcementTitle;
        private String announcementDescription;
        private ZonedDateTime publishDate;
        private ZonedDateTime closeDate;
        @Enumerated(EnumType.STRING)
        private Enum announcementDisplay;
        @Column(name = "categoriesCategoryId")
        private Integer categoryId;


}
