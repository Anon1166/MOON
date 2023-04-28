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
    private Integer categoryId ;
    private String categoryName ;


}
