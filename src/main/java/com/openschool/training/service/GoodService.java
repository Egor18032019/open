package com.openschool.training.service;

import com.openschool.training.annotation.TrackAsyncTime;
import com.openschool.training.annotation.TrackTime;
import com.openschool.training.models.MethodsWhitTimes;
import com.openschool.training.models.Pokemon;
import com.openschool.training.models.PokemonsModel;
import com.openschool.training.store.GoodRepositoryImpl;
import com.openschool.training.store.MethodEntity;
import com.openschool.training.store.PokemonEntity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoodService implements GoodServiceCommon {
    GoodRepositoryImpl goodRepository;

    public GoodService(GoodRepositoryImpl goodRepository) {
        this.goodRepository = goodRepository;
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
            Thread.sleep(1111);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public void save(PokemonsModel results) {
        // Обработка результата
        for (Pokemon point : results.getResults()) {
            Pokemon old = goodRepository.getPokemonByName(point.getName());
            if (old == null) {
                goodRepository.saveNewPokemons(new PokemonEntity(point.getName(), point.getUrl()));
            }
        }
    }

    @Override
    public MethodsWhitTimes getMeExecutionTimeAllMethods() {

        List<MethodEntity> storage = goodRepository.getAllMethodsTimes();
        MethodsWhitTimes methodsWhitTimes = new MethodsWhitTimes(storage);
        return methodsWhitTimes;
    }

    @Override
    public Map<String, Double> getAllMethodsAverageTime() {
        List<MethodEntity> storage = goodRepository.getAllMethodsTimes();
        Map<String, Double> storageAveragesTime = new HashMap<>();
        for (MethodEntity entry : storage) {
            String key = entry.getName();
            List<Long> value = entry.getTimes();
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

    @Override
    public Map<String, Long> getAllMethodsTotalExecutionTime() {
        List<MethodEntity> storage = goodRepository.getAllMethodsTimes();
        Map<String, Long> storageTotalTime = new HashMap<>();
        for (MethodEntity entry : storage) {
            String key = entry.getName();
            List<Long> value = entry.getTimes();
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
