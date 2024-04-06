package com.openschool.training.service;

import com.openschool.training.models.*;

public interface GoodServiceCommon {

    PokemonsResponse getAllPokemons(int limit, int offset);

    MethodAndTimesListResponse getMeExecutionTimeAllMethods();

    Pokemon getPokemonByName(String name) throws InterruptedException;

    MethodWhitAverageTimeListResponse getAllMethodsAverageTime();
    MethodAndTotalTimesListResponse getAllMethodsTotalExecutionTime();
}
