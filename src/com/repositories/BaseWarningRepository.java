package com.repositories;

import com.entity.BaseWarning;
import org.springframework.data.repository.CrudRepository;

public interface BaseWarningRepository extends CrudRepository<BaseWarning,Integer> ,BaseWarningRepositoryCustom {
}
