package com.openschool.training.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "отдает имя метода и среднее время за сколько данный метод выполняется.")
public class MethodWhitAverageTime {
    private String name;
    private Double average;
}
