package com.openschool.training.models;

import com.openschool.training.store.MethodEntity;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MethodsWhitTimes {
    List<MethodEntity> result;
}
