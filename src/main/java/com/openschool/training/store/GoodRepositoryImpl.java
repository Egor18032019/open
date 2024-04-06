package com.openschool.training.store;

import com.openschool.training.models.Pokemon;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoodRepositoryImpl implements GoodRepository {
    PokemonsRepository pokemonsRepository;
    MethodEntityRepository methodEntityRepository;

    public GoodRepositoryImpl(PokemonsRepository pokemonsRepository, MethodEntityRepository methodEntityRepository) {
        this.pokemonsRepository = pokemonsRepository;
        this.methodEntityRepository = methodEntityRepository;
    }


    @Override
    @Transactional
    public void add(String name, long time) {
        MethodEntity methodEntity = methodEntityRepository.findByName(name);
        if (methodEntity != null) {
            List<Long> exTimes = methodEntity.getTimes();
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
        return null;
    }
    @Override
    public List<MethodEntity> getAllMethodsTimes() {
        return methodEntityRepository.findAll();
    }
    @Override
    public void saveNewPokemons(PokemonEntity pokemonEntity) {
        System.out.println(pokemonEntity.getName());
        pokemonsRepository.save(pokemonEntity);
    }
}
