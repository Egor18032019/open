package com.openschool.training.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "methods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MethodEntity extends AbstractBaseEntity {
    @Column()
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "times", joinColumns = @JoinColumn(name = "method_id"))
    @Column(name = "times")
    private List<Long> times;
//    private Set<Long> times;
}
