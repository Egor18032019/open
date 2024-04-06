package com.openschool.training.controllers;

import com.openschool.training.models.MethodsWhitTimes;
import com.openschool.training.models.Pokemon;
import com.openschool.training.models.PokemonsModel;
import com.openschool.training.service.GoodService;
import com.openschool.training.store.MethodEntity;
import com.openschool.training.utils.EndPoint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public PokemonsModel getAllPokemons(
            @RequestParam int limit,
            @RequestParam(defaultValue = "0") int offset) {

        return goodService.getAllPokemons(limit, offset);
    }

    /**
     * метод для получение всех методов и списка того за сколько медот выполнился
      * @return
     */
    @GetMapping(value = EndPoint.execution)
    public MethodsWhitTimes getMeExecutionTimeAllMethods() {

        return goodService.getMeExecutionTimeAllMethods();
    }

    @GetMapping(value = EndPoint.average)
    public Map<String, Double> getAllMethodsAverageExecutionTime() {

        return goodService.getAllMethodsAverageTime();
    }

    @GetMapping(value = EndPoint.total)
    public Map<String, Long> getAllMethodsTotalExecutionTime() {

        return goodService.getAllMethodsTotalExecutionTime();
    }
}
