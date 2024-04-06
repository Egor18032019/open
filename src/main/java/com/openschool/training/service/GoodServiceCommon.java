package com.openschool.training.service;

import com.openschool.training.models.*;

public interface GoodServiceCommon {

    PokemonsModel getAllPokemons(int limit, int offset);

    MethodAndTimesList getMeExecutionTimeAllMethods();

    Pokemon getPokemonByName(String name) throws InterruptedException;

    MethodWhitAverageTimeList getAllMethodsAverageTime();
    MethodAndTotalTimesList getAllMethodsTotalExecutionTime();
}
