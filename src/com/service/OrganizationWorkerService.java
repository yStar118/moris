package com.service;

import com.sys.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 高宇飞 on 2017/7/25.
 * 职工   （调用系统用户表）
 */
@Service
public class OrganizationWorkerService {

    @Autowired
    private SysUserDao sysUserDao;


}
