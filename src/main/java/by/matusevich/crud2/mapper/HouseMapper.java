package by.matusevich.crud2.mapper;

import by.matusevich.crud2.dto.HouseDto;
import by.matusevich.crud2.model.House;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface HouseMapper {

    House toEntity(HouseDto dto);

    HouseDto toDto(House house);
}
