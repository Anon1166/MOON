package int221.MOON.Dto;

import int221.MOON.Enum;
import int221.MOON.validation.ValidDate;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class InputAnnouncesDTO {


    private Integer id;
    @NotBlank(message = "Title is required") @Size(min = 1, max = 200, message = "Title must be between 1 and 200")
    private String announcementTitle;

    @NotBlank(message = "Description is required") @Size(min = 1, max = 10000, message = "Description must be between 1 and 10000")
    private String announcementDescription;

    @ValidDate
    private ZonedDateTime publishDate;
    @ValidDate
    private ZonedDateTime closeDate;

    @Enumerated(EnumType.STRING)
    private Enum announcementDisplay;

    @Column(name = "categoriesCategoryId")
    @NotBlank(message = "categoryId does not exits")
    private Integer categoryId;


}
