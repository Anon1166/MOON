package int221.MOON.Dto;

import int221.MOON.Enum;
import int221.MOON.validation.ValidDate;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.ZonedDateTime;

@Getter
@Setter
@Validated
@ValidDate
public class InputAnnouncesDTO {


    private Integer id;
    @NotEmpty(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200")
    private String announcementTitle;

    @NotEmpty(message = "Description is required")
    @Size(min = 1, max = 10000, message = "Description must be between 1 and 10000")
    private String announcementDescription;

    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;

    @Enumerated(EnumType.STRING)
    private Enum announcementDisplay;

    @Column(name = "categoriesCategoryId")
    @NotNull(message = "categoryId cannot be null")
    private Integer categoryId;
}
