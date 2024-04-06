package com.openschool.training.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ API включает в себя Список выполение всех методов со среднем временем для каждого метода.")
public class MethodWhitAverageTimeListResponse {
    List<MethodWhitAverageTime> result;
}
