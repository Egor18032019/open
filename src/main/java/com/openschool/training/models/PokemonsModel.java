package com.openschool.training.models;

import lombok.*;

import java.util.ArrayList;

/**
 * Класс описывает полученного лимитированного списка покемонов
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonsModel {
    int count;
    String next;
    String previous;
    ArrayList<Pokemon> results;
}
