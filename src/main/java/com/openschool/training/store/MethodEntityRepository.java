package com.openschool.training.store;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodEntityRepository extends JpaRepository<MethodEntity, Long> {
    MethodEntity findByName(String name);
}
