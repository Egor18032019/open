package com.openschool.training.service;

import com.openschool.training.models.Pokemon;
import com.openschool.training.models.PokemonsModel;
import com.openschool.training.store.GoodRepositoryImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoodService implements GoodServiceCommon {
    GoodRepositoryImpl goodRepository;

    public GoodService(GoodRepositoryImpl goodRepository) {
        this.goodRepository = goodRepository;
    }

    @Override
    public Pokemon getPokemonByName(String name) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        long id = Thread.currentThread().getId();
        System.out.println("getPokemonByName . Thread id is:  " + id);
        Thread.sleep(11111);
        Pokemon pokemon = goodRepository.getPokemonByName(name);
        //todo получать имя метода отдельно
        long endTime = System.currentTimeMillis();
        goodRepository.add("getUserByEmail", endTime - startTime);
        return pokemon;
    }

    @Override
    @Async
    public CompletableFuture<PokemonsModel> getAllPokemons(int limit, int offset) {
        long startTime = System.currentTimeMillis();
        long id = Thread.currentThread().getId();
        System.out.println("getAllPokemons. Thread id is:  " + id);
        if (offset > limit) offset = 0;
        if (limit < 1) limit = 1;
        RestTemplate restTemplate = new RestTemplate();
        //todo поправить
        PokemonsModel results = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?limit=" + limit + "&offset=" + offset, PokemonsModel.class);
        long endTime = System.currentTimeMillis();
        goodRepository.add("getAllPokemons", endTime - startTime);
        return CompletableFuture.completedFuture(results);
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
