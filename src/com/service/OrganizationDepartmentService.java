package com.service;

import com.common.service.BaseService;
import com.entity.OrganizationDepartment;
import com.repositories.OrganizationDepartmentRepository;
import com.searchVO.OrganizationDepartmentSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/5/25.
 * 部门Service
 */
@Service
public class OrganizationDepartmentService implements BaseService<OrganizationDepartment, String> {

    @Autowired
    private OrganizationDepartmentRepository organizationDepartmentRepository;

    public List<OrganizationDepartment> getList(OrganizationDepartmentSearchVO searchVO) {
        return organizationDepartmentRepository.getList(searchVO);
    }

    @Override
    public <S extends OrganizationDepartment> S save(S entity) {
        return organizationDepartmentRepository.save(entity);
    }

    public <S extends OrganizationDepartment> Iterable<S> save(List<S> entity) {
        return organizationDepartmentRepository.save(entity);
    }

    @Override
    public OrganizationDepartment findOne(String primaryKey) {
        return organizationDepartmentRepository.findOne(primaryKey);
    }

    @Override
    public List<OrganizationDepartment> findAll() {
        return IterableUtils.toList(organizationDepartmentRepository.findAll());
    }

    @Override
    public long count() {
        return organizationDepartmentRepository.count();
    }

    @Override
    public void delete(String id) {
        organizationDepartmentRepository.delete(id);
    }

    public OrganizationDepartment findOneByCode(String departmentCode) {
      return   organizationDepartmentRepository.findOneByCode(departmentCode);
    }
}
