package by.matusevich.crud2.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "house")
@EqualsAndHashCode
public class House {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

}
