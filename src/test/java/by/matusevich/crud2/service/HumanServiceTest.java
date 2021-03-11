package by.matusevich.crud2.service;

import by.matusevich.crud2.dto.HouseDto;
import by.matusevich.crud2.dto.HumanDto;
import by.matusevich.crud2.exception.NoSuchHumanFoundException;
import by.matusevich.crud2.mapper.HumanMapper;
import by.matusevich.crud2.model.Human;
import by.matusevich.crud2.repo.HumanRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:applicationTest.properties")
@Transactional
@ExtendWith(MockitoExtension.class)
@DirtiesContext
class HumanServiceTest {

    @Resource
    HumanService humanService;

    @MockBean
    HumanRepo humanRepo;
    @Resource
    HumanMapper mapper;

    HouseDto createHouse() {
        return new HouseDto(1L, "test house");
    }

    HumanDto createTestHuman() {
        return new HumanDto(1L, "test", List.of(createHouse()));
    }

    @Test
    void findNoneGetException() {
        Assertions.assertThrows(NoSuchHumanFoundException.class,
                () -> humanService.findById(null));
    }

    @Test
    void createHuman() {
        HumanDto testHuman = createTestHuman();
        humanService.createHuman(testHuman);
        Mockito.verify(humanRepo, Mockito.times(1))
                .save(mapper.toEntity(testHuman));
    }

    @Test
    void saveHuman() {
        final HumanDto dto = createTestHuman();


        given(humanRepo.save(mapper.toEntity(dto)))
                .willAnswer(invocation -> invocation.getArgument(0));
        HumanDto saved = humanService.createHuman(dto);
        assertNotNull(saved);

        verify(humanRepo).save(any(Human.class));
    }

    @Test
    void findById() {
        given(humanRepo.findById(1L))
                .willReturn(Optional.of(mapper.toEntity(createTestHuman())));
        HumanDto testHuman = createTestHuman();
        humanService.createHuman(testHuman);
        HumanDto foundHuman = humanService.findById(1L);
        assertEquals(testHuman.getId(), foundHuman.getId());
        assertEquals(testHuman.getHouseList().size(), foundHuman.getHouseList().size());
        assertEquals(testHuman.getName(), foundHuman.getName());

    }


    @Test
    void updateHuman() {
        HumanDto testHuman = createTestHuman();
        given(humanRepo.save(mapper.toEntity(testHuman)))
                .willReturn(mapper.toEntity(testHuman));

        final HumanDto expected = humanService
                .updateHuman(testHuman);

        assertNotNull(expected);
        verify(humanRepo).save(any(Human.class));

    }

    @Test
    void deleteHuman() {
        final Long humanId = 1L;
        humanService.deleteHuman(humanId);
        humanService.deleteHuman(humanId);

        verify(humanRepo, times(2)).deleteById(humanId);
    }

//    @Test
//
//    void findAll() {
//        List<HumanDto> humans = new ArrayList<>();
//        humans.add(new HumanDto(1L, "test1", Collections.singletonList(createHouse())));
//        humans.add(new HumanDto(2L, "test2", Collections.singletonList(createHouse())));
//        humans.add(new HumanDto(3L, "test3", Collections.singletonList(createHouse())));
//        Page<HumanDto> humanDtos = new PageImpl<>(humans);
//        List<Human> humanList = humans.stream().map(mapper::toEntity).collect(Collectors.toList());
//        when(humanRepo.findAll())
//                .thenReturn(humanList);
//        //TODO
////        assertEquals(service.findAll(PageRequest.of(0, 3)));
//    }
}