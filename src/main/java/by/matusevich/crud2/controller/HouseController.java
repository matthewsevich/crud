package by.matusevich.crud2.controller;

import by.matusevich.crud2.dto.HouseDto;
import by.matusevich.crud2.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/houses")
@Tag(name = "house", description = "House-API")
public class HouseController {
    private final HouseService service;

    @PostMapping("/create")

    @Operation(summary = "create house", description = "create house", tags = {"house"},
    security = @SecurityRequirement(name = "basicAuth"))
    public ResponseEntity<HouseDto> createHouse(HouseDto dto) {
        log.info("create house");
        return ResponseEntity.ok(service.createHouse(dto));
    }

    @Operation(summary = "get 1 house", description = "find house by id")
    @GetMapping("/{id}")
    public ResponseEntity<HouseDto> getHouse(@PathVariable @NonNull Long id) {
        log.info("get house with id " + id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/")
    @Operation(summary = "get all house on page", description = "get all houses on page")
    public ResponseEntity<List<HouseDto>> getAll(@NonNull Pageable page) {
        log.info("get all houses");
        return ResponseEntity.ok(service.getAll(page));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete house by id")
    public void deleteHouse(@PathVariable @NonNull Long id) {
        log.info("delete house with id" + id);
        service.deleteHouse(id);
    }

    @PutMapping("/update")
    @Operation(summary = "update mapping")
    public ResponseEntity<HouseDto> updateHouse(@NonNull HouseDto dto) {
        log.info("update house");
        return ResponseEntity.ok(service.updateHouse(dto));
    }
}
