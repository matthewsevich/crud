package by.matusevich.crud2.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "human")
@EqualsAndHashCode
public class Human {

    @Id
    @Column(name = "id")

    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "human_id")
    @EqualsAndHashCode.Exclude
    private List<House> houseList;
}
