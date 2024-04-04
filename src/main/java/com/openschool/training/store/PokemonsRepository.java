package com.openschool.training.store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonsRepository extends JpaRepository<PokemonEntity, Long> {
    PokemonEntity findByName(String name);
}
