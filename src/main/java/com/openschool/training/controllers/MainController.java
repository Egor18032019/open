package com.openschool.training.controllers;

import com.openschool.training.models.*;
import com.openschool.training.service.GoodService;
import com.openschool.training.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Основной контроллер.")
@RestController
@RequestMapping(value = EndPoint.api)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class MainController {

    GoodService goodService;
    @Operation(
            summary = "Получение покемона из БД",
            description = "Ждет на вход имя покемона."
    )
    @GetMapping(value = "/{name}")
    public Pokemon getPokemonByName(@PathVariable String name) throws InterruptedException {

        return goodService.getPokemonByName(name);
    }
    @Operation(
            summary = "Метод для получение всех покемонов."
    )
    @GetMapping(value = EndPoint.pokemons)
    public PokemonsResponse getAllPokemons(
            @RequestParam int limit,
            @RequestParam(defaultValue = "0") int offset) {

        return goodService.getAllPokemons(limit, offset);
    }
    @Operation(
            summary = "Метод для получение всех методов и списка того за сколько метод выполнился"
    )
    @GetMapping(value = EndPoint.execution)
    public MethodAndTimesListResponse getMeExecutionTimeAllMethods() {

        return goodService.getMeExecutionTimeAllMethods();
    }
    @Operation(
            summary = "Метод для получение всех методов и списка выполение всех методов со среднем временем для каждого метода."
    )
    @GetMapping(value = EndPoint.average)
    public MethodWhitAverageTimeListResponse getAllMethodsAverageExecutionTime() {

        return goodService.getAllMethodsAverageTime();
    }
    @Operation(
            summary = "Метод для получение всех методов и списка с общей суммой времени для каждого метода."
    )
    @GetMapping(value = EndPoint.total)
    public MethodAndTotalTimesListResponse getAllMethodsTotalExecutionTime() {

        return goodService.getAllMethodsTotalExecutionTime();
    }
}
