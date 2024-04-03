package com.openschool.training.store;

import com.openschool.training.models.Pokemon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GoodRepositoryImpl implements GoodRepository {
    private final Map<String, List<Long>> STORAGE_EXAMPLE = new ConcurrentHashMap<>();

    @Override
    public void add(String name, long time) {
        List<Long> value = STORAGE_EXAMPLE.getOrDefault(name, new ArrayList<>());
        value.add(time);
        STORAGE_EXAMPLE.put(name, value);
    }

    @Override
    public Pokemon getPokemonByName(String name) {
        Pokemon pokemon = new Pokemon("bulbasaur","https://pokeapi.co/api/v2/pokemon/1/");
        return pokemon;
    }

    @Override
    public void clear() {
        STORAGE_EXAMPLE.clear();
    }

    @Override
    public int getSizeStorage() {
        return STORAGE_EXAMPLE.size();
    }

    @Override
    public Map<String, List<Long>> getStorage() {
        return STORAGE_EXAMPLE;
    }
}
