package com.openschool.training.service;

import com.openschool.training.models.MethodsWhitTimes;
import com.openschool.training.models.Pokemon;
import com.openschool.training.models.PokemonsModel;
import com.openschool.training.store.MethodEntity;

import java.util.List;
import java.util.Map;

public interface GoodServiceCommon {

    PokemonsModel getAllPokemons(int limit, int offset);

    MethodsWhitTimes getMeExecutionTimeAllMethods();

    Pokemon getPokemonByName(String name) throws InterruptedException;

    Map<String, Double> getAllMethodsAverageTime();
    Map<String, Long> getAllMethodsTotalExecutionTime();
}
