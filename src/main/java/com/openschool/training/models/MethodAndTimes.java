package com.openschool.training.models;

import lombok.*;

import java.util.List;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MethodAndTimes {
    private String name;
    private List<Long> times;
}
