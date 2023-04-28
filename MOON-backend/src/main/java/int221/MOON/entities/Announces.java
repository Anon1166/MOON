package int221.MOON.entities;

import int221.MOON.Enum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table (name = "announcement")
public class Announces {
    @Id
    private Integer announcementId ;
    private String announcementTitle ;
    private String announcementDescription;
    private ZonedDateTime publishDate ;
    private ZonedDateTime closeDate ;
    @Enumerated(EnumType.STRING)
    private Enum announcementDisplay;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Categories categories ;


}
