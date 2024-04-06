package com.openschool.training.store;

import com.openschool.training.models.Pokemon;

import java.util.List;

public interface GoodRepository {
    void add(String name, long time);
    Pokemon getPokemonByName(String name);
    List<MethodEntity> getAllMethodsTimes();
    void saveNewPokemons(PokemonEntity pokemonEntity);
}
