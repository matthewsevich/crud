package by.matusevich.crud2.controller;

import by.matusevich.crud2.dto.HumanDto;
import by.matusevich.crud2.service.HumanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/humans")
@Tag(name = "human", description = "human-API")
public class HumanController {
    private final HumanService service;

    @Operation(summary = "create human", description = "create human", tags = {"human"})
    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "human created",
                    content = @Content(schema = @Schema(implementation = HumanDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Human already exists")})
    public ResponseEntity<HumanDto> createHuman(
            @Parameter(description = "Human to add. Cannot null or empty.",
                    required = true, schema = @Schema(implementation = HumanDto.class))
            @RequestBody HumanDto dto) {
        return ResponseEntity.ok(service.createHuman(dto));
    }

    @PutMapping("/update")
    @Operation(summary = "update human")
    public ResponseEntity<HumanDto> updateHuman(@RequestBody HumanDto dto) {
        return ResponseEntity.ok(service.updateHuman(dto));
    }

    @Operation(summary = "get one human with list of houses", description = "get human", tags = "human")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = HumanDto.class))),
            @ApiResponse(responseCode = "404", description = "human not found",
                    content = @Content(schema = @Schema(implementation = HumanDto.class)))})
    @GetMapping(value = "/{id}")
    public ResponseEntity<HumanDto> getHuman(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "get all humans", description = "get all humans", tags = "humans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = HumanDto.class))))
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<HumanDto>> getAll(@PageableDefault(page = 0, size = 10) Pageable page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    @Operation(summary = "delete human")
    @DeleteMapping("/delete/{id}")
    public void deleteHuman(@PathVariable @NonNull Long id) {
        service.deleteHuman(id);
    }
}
