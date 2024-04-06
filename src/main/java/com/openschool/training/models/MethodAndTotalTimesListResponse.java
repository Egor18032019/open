package com.openschool.training.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ API включает в себя Список всех методов с общей суммой времени для каждого метода")
public class MethodAndTotalTimesListResponse {
    List<MethodAndTotalTime> result;
}
