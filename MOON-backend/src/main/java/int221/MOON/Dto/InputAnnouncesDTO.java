package int221.MOON.Dto;

import int221.MOON.Enum;
import int221.MOON.validation.ValidDate;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.ZonedDateTime;

import static int221.MOON.Enum.Y;

@Getter
@Setter
@Validated
@ValidDate
public class InputAnnouncesDTO {


    private Integer id;
    @NotBlank(message = "must not be blank")
    @NotEmpty(message = "must not be null")
    @Size(min = 1, max = 200, message = "size must be between 1 and 200")
    private String announcementTitle;

    @NotBlank(message = "must not be blank")
    @NotEmpty(message = "must not be null")
    @Size(min = 1, max = 10000, message = "size must be between 1 and 10000")
    private String announcementDescription;

    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;


    @NotEmpty(message = "must be either 'Y' or 'N'")
    @NotNull(message = "must be either 'Y' or 'N'")
    @Enumerated(EnumType.STRING)
    private String announcementDisplay;

    @Column(name = "categoriesCategoryId")
    @NotNull(message = "must not be null")
    private Integer categoryId;
}
