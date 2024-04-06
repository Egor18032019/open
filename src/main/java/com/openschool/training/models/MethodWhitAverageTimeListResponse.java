package com.openschool.training.models;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MethodWhitAverageTimeListResponse {
    List<MethodWhitAverageTime> result;
}