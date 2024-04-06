package com.openschool.training.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity extends AbstractBaseEntity {
    @Column()
    private String name;
    @Column()
    private String url;

}


