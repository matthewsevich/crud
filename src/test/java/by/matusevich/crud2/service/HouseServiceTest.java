package by.matusevich.crud2.service;

import by.matusevich.crud2.dto.HouseDto;
import by.matusevich.crud2.exception.NoSuchHouseFoundException;
import by.matusevich.crud2.mapper.HouseMapper;
import by.matusevich.crud2.model.House;
import by.matusevich.crud2.repo.HouseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
class HouseServiceTest {

    @Resource
    HouseService houseService;

    @MockBean
    HouseRepo houseRepo;

    @Resource
    HouseMapper houseMapper;

    HouseDto createHouse() {
        return new HouseDto(1L, "test house");
    }

    @Test
    void findNoneGetException() {
        Assertions.assertThrows(NoSuchHouseFoundException.class,
                () -> houseService.findById(null));
    }

    @Test
    void createHouseTest() {
        HouseDto dto = createHouse();
        houseService.createHouse(dto);
        verify(houseRepo, Mockito.times(1))
                .save(houseMapper.toEntity(dto));
        verify(houseRepo).save(any(House.class));
    }

    @Test
    void findById() {
        given(houseRepo.findById(1L))
                .willReturn(Optional.of(houseMapper.toEntity(createHouse())));
        HouseDto testHouse = createHouse();
        houseService.createHouse(testHouse);
        HouseDto foundHouse = houseService.findById(1L);
        assertEquals(foundHouse.getId(), testHouse.getId());
        assertEquals(foundHouse.getDescription(), testHouse.getDescription());
    }

//    @Test
//    void getAll() {
//        List<HouseDto> houses = new ArrayList<>();
//        houses.add(new HouseDto(1L, "one"));
//        houses.add(new HouseDto(2L, "two"));
//        houses.add(new HouseDto(3L, "three"));
//
//
//        List<House> houseList = houses.stream().map(houseMapper::toEntity).collect(Collectors.toList());
//
//        Page<House> found = new PageImpl<>(houseList);
//
//
//        when(houseRepo.findAll(any(Pageable.class)))
//                .thenReturn(found);
//
//        List<HouseDto> all = houseService.getAll(any(Pageable.class));
//
//
//        assertEquals(found, all);
//    }

    @Test
    void deleteHouse() {
        final Long houseId = 1L;
        houseService.deleteHouse(houseId);
        houseService.deleteHouse(houseId);
        houseService.deleteHouse(houseId);
        houseService.deleteHouse(houseId);

        verify(houseRepo, times(4)).deleteById(houseId);
    }

    @Test
    void updateHouse() {
        HouseDto testHouse = createHouse();
        given(houseRepo.save(houseMapper.toEntity(testHouse)))
                .willReturn(houseMapper.toEntity(testHouse));

        final HouseDto expected = houseService
                .updateHouse(testHouse);

        assertNotNull(expected);
        verify(houseRepo).save(any(House.class));
    }
}