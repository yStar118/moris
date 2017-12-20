package com.repositories;

import com.app.dto.AppdAddressBook;
import com.entity.OrganizationDepartment;
import com.searchVO.OrganizationDepartmentSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/8.
 * 部门自定义Repository
 */
public interface OrganizationDepartmentRepositoryCustom {

    List<OrganizationDepartment> getList(OrganizationDepartmentSearchVO searchVO);

    OrganizationDepartment findOneByCode(String departmentCode);

    List<AppdAddressBook> getListForApp();
}
