package com.openschool.training.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ API включает в себя Список выполнения всех методов + список со временем выполнения каждого метода при каждом вызове метода.")
public class MethodAndTimesListResponse {
    List<MethodAndTimes> result;
}
