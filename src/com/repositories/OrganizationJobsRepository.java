package com.repositories;

import com.entity.OrganizationJobs;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/24.
 */
public interface OrganizationJobsRepository extends CrudRepository<OrganizationJobs, Integer>, OrganizationJobsRepositoryCustom {

    List<OrganizationJobs> findByDepartmentCode(String departmentCode);

    List<OrganizationJobs> findByCode(String code);
}
