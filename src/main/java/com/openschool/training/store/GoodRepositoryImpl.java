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
    PokemonsRepository pokemonsRepository;
    MethodEntityRepository methodEntityRepository;


    public GoodRepositoryImpl(PokemonsRepository pokemonsRepository, MethodEntityRepository methodEntityRepository) {
        this.pokemonsRepository = pokemonsRepository;
        this.methodEntityRepository = methodEntityRepository;
    }


    @Override
    public void add(String name, long time) {
        MethodEntity methodEntity = methodEntityRepository.findByName(name);
        if (methodEntity != null) {
            List<Long> exTimes =methodEntity.getTimes();
            exTimes.add(time);
            methodEntity.setTimes(exTimes);
        } else {
            List<Long> exTimes = new ArrayList<>();
            exTimes.add(time);
            methodEntity = new MethodEntity(name, exTimes);
        }
        methodEntityRepository.save(methodEntity);
    }

    @Override
    public Pokemon getPokemonByName(String name) {
        PokemonEntity pokemon = pokemonsRepository.findByName(name);
        if (pokemon != null) return new Pokemon(pokemon.getName(), pokemon.getUrl());
        return new Pokemon();
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
