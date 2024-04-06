package com.openschool.training.service;

import com.openschool.training.annotation.TrackAsyncTime;
import com.openschool.training.annotation.TrackTime;
import com.openschool.training.models.*;
import com.openschool.training.store.GoodRepositoryImpl;
import com.openschool.training.store.MethodEntity;
import com.openschool.training.store.PokemonEntity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

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
    public MethodAndTimesList getMeExecutionTimeAllMethods() {
        List<MethodEntity> storage = goodRepository.getAllMethodsTimes();
        List<MethodAndTimes> result = storage.stream().map(o->{
            return new MethodAndTimes(o.getName(),o.getTimes());
        }).toList();
        MethodAndTimesList methodsWhitTimes = new MethodAndTimesList(result);
        return methodsWhitTimes;
    }

    @Override
    public MethodWhitAverageTimeList getAllMethodsAverageTime() {
        List<MethodEntity> storage = goodRepository.getAllMethodsTimes();
        List<MethodWhitAverageTime> result = new ArrayList<>();
        for (MethodEntity entry : storage) {
            String name = entry.getName();
            Double average = getAverage(entry.getTimes());
            MethodWhitAverageTime methodsWhitTimes = new MethodWhitAverageTime(name,average);
            result.add(methodsWhitTimes);
        }
        MethodWhitAverageTimeList answer = new MethodWhitAverageTimeList(result);
        return answer;
    }

    public static double getAverage(List<Long> numbers) {
        // Используем метод stream() для преобразования списка в поток
        DoubleSummaryStatistics stats = numbers.stream().mapToDouble(num -> num).summaryStatistics();
        // Возвращаем среднее значение
        return stats.getAverage();
    }

    @Override
    public MethodAndTotalTimesList getAllMethodsTotalExecutionTime() {
        List<MethodEntity> storage = goodRepository.getAllMethodsTimes();
        List<MethodAndTotalTime> result= new ArrayList<>();
        for (MethodEntity entry : storage) {
            String name = entry.getName();
            Long total = getTotal(entry.getTimes());
            MethodAndTotalTime methodsWhitTimes = new MethodAndTotalTime(name,total);
            result.add(methodsWhitTimes);
        }
        MethodAndTotalTimesList answer = new MethodAndTotalTimesList(result);
        return answer;
    }

    public static Long getTotal(List<Long> numbers) {
        Long totalSum = 0L;
        for (Long value : numbers) {
            totalSum += value;
        }
        return totalSum;
    }
}
