package by.matusevich.crud2.mapper;

import by.matusevich.crud2.dto.HumanDto;
import by.matusevich.crud2.model.Human;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
injectionStrategy = InjectionStrategy.FIELD,
unmappedTargetPolicy = ReportingPolicy.ERROR,
nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface HumanMapper {

    Human toEntity(HumanDto dto);

    HumanDto toDTO(Human human);
}
