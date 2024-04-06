package com.openschool.training.controllers;

import com.openschool.training.models.*;
import com.openschool.training.service.GoodService;
import com.openschool.training.utils.EndPoint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = EndPoint.api)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class PageController {

    GoodService goodService;

    @GetMapping(value = "/{name}")
    public Pokemon getPokemonByName(@PathVariable String name) throws InterruptedException {

        return goodService.getPokemonByName(name);
    }

    @GetMapping(value = EndPoint.pokemons)
    public PokemonsResponse getAllPokemons(
            @RequestParam int limit,
            @RequestParam(defaultValue = "0") int offset) {

        return goodService.getAllPokemons(limit, offset);
    }

    /**
     * метод для получение всех методов и списка того за сколько медот выполнился
      * @return MethodsWhitTimes
     */
    @GetMapping(value = EndPoint.execution)
    public MethodAndTimesListResponse getMeExecutionTimeAllMethods() {

        return goodService.getMeExecutionTimeAllMethods();
    }

    @GetMapping(value = EndPoint.average)
    public MethodWhitAverageTimeListResponse getAllMethodsAverageExecutionTime() {

        return goodService.getAllMethodsAverageTime();
    }

    @GetMapping(value = EndPoint.total)
    public MethodAndTotalTimesListResponse getAllMethodsTotalExecutionTime() {

        return goodService.getAllMethodsTotalExecutionTime();
    }
}
