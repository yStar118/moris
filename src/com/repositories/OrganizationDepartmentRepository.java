package com.repositories;

import com.entity.OrganizationDepartment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/8.
 * 部门Repository
 */
public interface OrganizationDepartmentRepository extends CrudRepository<OrganizationDepartment, String>, OrganizationDepartmentRepositoryCustom {

    List<OrganizationDepartment> findByCode(String code);
}
