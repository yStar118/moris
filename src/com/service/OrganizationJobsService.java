package com.service;

import com.common.service.BaseService;
import com.entity.OrganizationJobs;
import com.repositories.OrganizationJobsRepository;
import com.searchVO.OrganizationJobsSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/24.
 * 岗位
 */
@Service
public class OrganizationJobsService implements BaseService<OrganizationJobs, Integer> {

    @Autowired
    private OrganizationJobsRepository organizationJobsRepository;

    @Override
    public <S extends OrganizationJobs> S save(S entity) {
        return organizationJobsRepository.save(entity);
    }

    public <S extends OrganizationJobs> Iterable<S> save(List<S> entity) {
        return organizationJobsRepository.save(entity);
    }

    @Override
    public OrganizationJobs findOne(Integer primaryKey) {
        return organizationJobsRepository.findOne(primaryKey);
    }

    @Override
    public List<OrganizationJobs> findAll() {
        return IterableUtils.toList(organizationJobsRepository.findAll());
    }

    public List<OrganizationJobs> getList(OrganizationJobsSearchVO searchVO) {
        return organizationJobsRepository.getList(searchVO);
    }

    public List<OrganizationJobs> findByDepartmentCode(String departmentCode) {
        return organizationJobsRepository.findByDepartmentCode(departmentCode);
    }

    @Override
    public long count() {
        return organizationJobsRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        organizationJobsRepository.delete(primaryKey);
    }

    public OrganizationJobs findOneByCode(String code) {
        return organizationJobsRepository.findOneByCode(code);
    }
}
