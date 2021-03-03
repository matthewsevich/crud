package by.matusevich.crud2.service;

import by.matusevich.crud2.dto.HouseDto;
import by.matusevich.crud2.exception.NoSuchHouseFoundException;
import by.matusevich.crud2.mapper.HouseMapper;
import by.matusevich.crud2.model.House;
import by.matusevich.crud2.repo.HouseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepo repo;
    private final HouseMapper mapper;

    public HouseDto createHouse(HouseDto dto) {
        House savedHouse = repo.save(mapper.toEntity(dto));
        return mapper.toDto(savedHouse);
    }

    @Cacheable("house-id")
    public HouseDto findById(Long id) {
        House foundHouse = repo.findById(id).orElseThrow(() -> new NoSuchHouseFoundException("no House found"));
        return mapper.toDto(foundHouse);
    }

    @Cacheable("houses")
    public List<HouseDto> getAll(Pageable page) {
        return repo.findAll(page)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteHouse(Long id) {
        repo.deleteById(id);
    }

    public HouseDto updateHouse(HouseDto dto) {
        House updatedHouse = mapper.toEntity(dto);
        return mapper.toDto(repo.save(updatedHouse));

    }
}
