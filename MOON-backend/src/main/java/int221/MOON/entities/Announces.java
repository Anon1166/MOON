package int221.MOON.entities;


import int221.MOON.Enum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;


@Getter
@Setter
@Entity
@Table (name = "announcement")
public class Announces {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="announcementId")
    private Integer id ;

    @Column(name="announcementTitle")
    private String announcementTitle ;

    @Column(name="announcementDescription")
    private String announcementDescription;

    @Column(name="publishDate")
    private ZonedDateTime publishDate ;

    @Column(name="closeDate")
    private ZonedDateTime closeDate ;

    @Column(name ="viewCount")
    private  Integer count;

    @Enumerated(EnumType.STRING)
    private Enum announcementDisplay;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Categories categories ;


}
