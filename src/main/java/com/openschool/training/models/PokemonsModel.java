package com.openschool.training.models;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс описывает полученного лимитированного списка покемонов
 */
@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonsModel {
    int count;
    String next;
    String previous;
    List<Pokemon> results;
}
