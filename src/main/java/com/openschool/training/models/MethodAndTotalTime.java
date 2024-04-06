package com.openschool.training.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "отдает имя метода и общее время выполнение метода за всё время работы приложения.")
public class MethodAndTotalTime {
    private String name;
    private Long total;
}
