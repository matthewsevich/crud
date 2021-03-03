package by.matusevich.crud2.controller;

import by.matusevich.crud2.dto.HouseDto;
import by.matusevich.crud2.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/houses")
@Tag(name = "house", description = "House-API")
public class HouseController {
    private final HouseService service;

    @PostMapping("/create")

    @Operation(summary = "create house", description = "create house", tags = {"house"})
    public ResponseEntity<HouseDto> createHouse(HouseDto dto) {
        return ResponseEntity.ok(service.createHouse(dto));
    }

    @Operation(summary = "get 1 house", description = "find house by id")
    @GetMapping("/{id}")
    public ResponseEntity<HouseDto> getHouse(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/")
    @Operation(summary = "get all house on page", description = "get all houses on page")
    public ResponseEntity<List<HouseDto>> getAll(@NonNull Pageable page) {
        return ResponseEntity.ok(service.getAll(page));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete house by id")
    public void deleteHouse(@PathVariable @NonNull Long id) {
        service.deleteHouse(id);
    }

    @PutMapping("/update")
    @Operation(summary = "update mapping")
    public ResponseEntity<HouseDto> updateHouse(@NonNull HouseDto dto) {
        return ResponseEntity.ok(service.updateHouse(dto));
    }
}
