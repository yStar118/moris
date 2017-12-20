package com.repositories;

import com.entity.OrganizationEnterprise;
import com.searchVO.OrganizationEnterpriseSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/22.
 * 企业
 */
public interface OrganizationEnterpriseRepositoryCustom {

    List<OrganizationEnterprise> getList(OrganizationEnterpriseSearchVO searchVO);
}
