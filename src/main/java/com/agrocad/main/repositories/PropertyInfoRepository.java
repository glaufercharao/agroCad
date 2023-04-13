package com.agrocad.main.repositories;

import com.agrocad.main.entities.PropertyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyInfoRepository extends JpaRepository<PropertyInfo, Long> {
}
