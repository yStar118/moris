package com.repositories;

import com.entity.OrganizationEnterprise;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 1553280431@qq.com on 2017/6/22.
 * 企业
 */
public interface OrganizationEnterpriseRepository extends CrudRepository<OrganizationEnterprise, Integer>, OrganizationEnterpriseRepositoryCustom {
}
