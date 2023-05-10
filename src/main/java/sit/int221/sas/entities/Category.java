package sit.int221.sas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name = "categoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "categoryName", nullable = false, unique = true)
    @NotBlank(message = "Category name is required")
    @Size(max = 50, message = "Category name cannot be longer than 50 characters")
    private String categoryName;

}
