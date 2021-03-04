package by.matusevich.crud2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanDto {

    private Long id;
    private String name;

    private List<HouseDto> houseList;

}
