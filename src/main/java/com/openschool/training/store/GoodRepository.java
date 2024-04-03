package com.openschool.training.store;

import com.openschool.training.models.Pokemon;

import java.util.List;
import java.util.Map;

public interface GoodRepository {
    void add(String name, long time);

    int getSizeStorage();

    void clear();

    Pokemon getPokemonByName(String name);

    Map<String, List<Long>> getStorage();
}
