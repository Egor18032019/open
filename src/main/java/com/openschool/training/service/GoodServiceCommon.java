package com.openschool.training.service;

import com.openschool.training.models.Pokemon;
import com.openschool.training.models.PokemonsModel;

import java.util.List;
import java.util.Map;

public interface GoodServiceCommon {

    PokemonsModel getAllPokemons(int limit, int offset);

    Map<String, List<Long>> getMeExecutionTimeAllMethods();

    Pokemon getPokemonByName(String name) throws InterruptedException;

    Map<String, Double> getAllMethodsAverageTime();
}
