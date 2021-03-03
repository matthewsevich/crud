package by.matusevich.crud2.service;

import by.matusevich.crud2.dto.HumanDto;
import by.matusevich.crud2.exception.NoSuchHumanFoundException;
import by.matusevich.crud2.mapper.HumanMapper;
import by.matusevich.crud2.model.Human;
import by.matusevich.crud2.repo.HumanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HumanService {
    private final HumanRepo repo;
    private final HumanMapper mapper;

    public HumanDto createHuman(HumanDto dto) {
        Human savedHuman = repo.save(mapper.toEntity(dto));
        return mapper.toDTO(savedHuman);
    }

    @Cacheable("human-id")
    public HumanDto findById(Long id) {
        Human foundHuman = repo.findById(id).orElseThrow(()->new NoSuchHumanFoundException("no human found"));
        return mapper.toDTO(foundHuman);
    }

    @Cacheable("humans")
    public List<HumanDto> findAll(Pageable page) {
        return repo.findAll(page)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public HumanDto updateHuman(HumanDto dto) {
        Human human = mapper.toEntity(dto);
        return mapper
                .toDTO(repo
                .save(human));
    }

    public void deleteHuman(Long id)     {
            repo.deleteById(id);
    }
}
