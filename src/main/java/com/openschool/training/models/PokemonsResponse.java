package com.openschool.training.models;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Ответ API содержит кол-во покемонов" +
        "+ URL-адрес следующей страницы в списке" +
        "+ URL-адрес предыдущей страницы в списке" +
        "+ Список ресурсов API.")
public class PokemonsResponse {
    int count;
    String next;
    String previous;
    List<Pokemon> results;
}
