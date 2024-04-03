package com.openschool.training.models;

import lombok.*;

/**
 * Класс описывает покемона (name и url)
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
