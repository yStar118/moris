package com.repositories;

import com.app.dto.AppdAddressBook;
import com.entity.OrganizationJobs;
import com.searchVO.OrganizationJobsSearchVO;

import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/24.
 * 岗位
 */
public interface OrganizationJobsRepositoryCustom {

    List<OrganizationJobs> getList(OrganizationJobsSearchVO searchVO);

    OrganizationJobs  findOneByCode(String code);

    List<AppdAddressBook> getListForApp();
}
