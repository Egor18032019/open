package com.openschool.training.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Класс описывает покемона (name и url)
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "отдает Имя покемона и url который ведет на страницу с полным описание этого покемона.")
public class Pokemon {
    /**
     * Имя покемона
     */
    private String name;
    /**
     * url который ведет на страницу с полным описание этого покемона
     */
    private String url;
}
