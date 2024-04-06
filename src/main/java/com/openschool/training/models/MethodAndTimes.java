package com.openschool.training.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "отдает имя метода и список за сколько данный метод выполнился.")
public class MethodAndTimes {
    private String name;
    private List<Long> times;
}
