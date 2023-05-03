package int221.MOON.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table (name = "category")
public class Categories {
    @Id
    @Column(name="categoryId")
    private Integer categoryId ;
    @Column(name="categoryName")
    private String categoryName ;


}
