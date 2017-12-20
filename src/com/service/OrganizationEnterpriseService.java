package com.service;

import com.common.service.BaseService;
import com.entity.OrganizationEnterprise;
import com.repositories.OrganizationEnterpriseRepository;
import com.searchVO.OrganizationEnterpriseSearchVO;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/22.
 * 企业
 */
@Service
public class OrganizationEnterpriseService implements BaseService<OrganizationEnterprise, Integer> {

    @Autowired
    private OrganizationEnterpriseRepository organizationEnterpriseRepository;

    @Override
    public <S extends OrganizationEnterprise> S save(S entity) {
        return organizationEnterpriseRepository.save(entity);
    }

    public <S extends OrganizationEnterprise> Iterable<S> save(List<S> entity) {
        return organizationEnterpriseRepository.save(entity);
    }

    @Override
    public OrganizationEnterprise findOne(Integer primaryKey) {
        return organizationEnterpriseRepository.findOne(primaryKey);
    }

    @Override
    public List<OrganizationEnterprise> findAll() {
        return IterableUtils.toList(organizationEnterpriseRepository.findAll());
    }

    public List<OrganizationEnterprise> getList(OrganizationEnterpriseSearchVO searchVO) {
        return organizationEnterpriseRepository.getList(searchVO);
    }

    @Override
    public long count() {
        return organizationEnterpriseRepository.count();
    }

    @Override
    public void delete(Integer primaryKey) {
        organizationEnterpriseRepository.delete(primaryKey);
    }
}
