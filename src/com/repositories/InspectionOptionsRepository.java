package com.repositories;

import com.entity.InspectionOptions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/30.
 * 检查项
 */
public interface InspectionOptionsRepository  extends CrudRepository<InspectionOptions,Integer>,InspectionOptionsRepositoryCustom{

    List<InspectionOptions> findByName(String name);
}
