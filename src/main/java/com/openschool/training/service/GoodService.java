package com.openschool.training.service;

import com.openschool.training.annotation.TrackAsyncTime;
import com.openschool.training.annotation.TrackTime;
import com.openschool.training.models.Pokemon;
import com.openschool.training.models.PokemonsModel;
import com.openschool.training.store.GoodRepositoryImpl;
import com.openschool.training.store.PokemonEntity;
import com.openschool.training.store.PokemonsRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoodService implements GoodServiceCommon {
    GoodRepositoryImpl goodRepository;
    static PokemonsRepository pokemonsRepository;

    public GoodService(GoodRepositoryImpl goodRepository, PokemonsRepository pokemonsRepository) {
        this.goodRepository = goodRepository;
        GoodService.pokemonsRepository = pokemonsRepository;
    }

    @Override
    @TrackTime(className = "getPokemonByName") //todo переделать получение имени
    public Pokemon getPokemonByName(String name) throws InterruptedException {
        long id = Thread.currentThread().getId();
        System.out.println("getPokemonByName . Thread id is:  " + id);
        Thread.sleep(1111);
        Pokemon pokemon = goodRepository.getPokemonByName(name);

        return pokemon;
    }

    @Override
    @TrackAsyncTime()
    public PokemonsModel getAllPokemons(int limit, int offset) {
        long id = Thread.currentThread().getId();
        System.out.println("getAllPokemons. Thread id is:  " + id);
        if (offset > limit) offset = 0;
        if (limit < 1) limit = 1;
        RestTemplate restTemplate = new RestTemplate();
        //todo поправить
        String url = "https://pokeapi.co/api/v2/pokemon?limit=" + limit + "&offset=" + offset;
        System.out.println(url);
        PokemonsModel results = restTemplate.getForObject(url, PokemonsModel.class);
        assert results != null;
        save(results);
        try {
            Thread.sleep(11111);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public static void save(PokemonsModel results) {
        // Обработка результата
        for (Pokemon point : results.getResults()) {
            PokemonEntity old = pokemonsRepository.findByName(point.getName());
            if (old == null) {
                pokemonsRepository.save(new PokemonEntity(point.getName(), point.getUrl()));
            }
        }
    }

    @Override
    public Map<String, List<Long>> getMeExecutionTimeAllMethods() {
        long id = Thread.currentThread().getId();
        System.out.println("getMeExecutionTimeAllMethods. Thread id is:  " + id);
        return goodRepository.getStorage();
    }

    @Override
    public Map<String, Double> getAllMethodsAverageTime() {
        Map<String, List<Long>> storage = goodRepository.getStorage();
        Map<String, Double> storageAveragesTime = new HashMap<>();
        for (Map.Entry<String, List<Long>> entry : storage.entrySet()) {
            String key = entry.getKey();
            List<Long> value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
            storageAveragesTime.put(key, getAverage(value));
        }
        return storageAveragesTime;
    }

    public static double getAverage(List<Long> numbers) {
        // Используем метод stream() для преобразования списка в поток
        DoubleSummaryStatistics stats = numbers.stream().mapToDouble(num -> num).summaryStatistics();

        // Возвращаем среднее значение
        return stats.getAverage();
    }

    public Map<String, Long> getAllMethodsTotalExecutionTime() {
        Map<String, List<Long>> storage = goodRepository.getStorage();
        Map<String, Long> storageTotalTime = new HashMap<>();
        for (Map.Entry<String, List<Long>> entry : storage.entrySet()) {
            String key = entry.getKey();
            List<Long> value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
            storageTotalTime.put(key, getTotal(value));
        }
        return storageTotalTime;
    }

    public static Long getTotal(List<Long> numbers) {
        Long totalSum = 0L;
        for (Long value : numbers) {
            totalSum += value;
        }

        return totalSum;
    }


}
