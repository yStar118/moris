package com.service;

import com.app.dto.AppdAddressBook;
import com.repositories.OrganizationDepartmentRepository;
import com.repositories.OrganizationJobsRepository;
import com.sys.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2017/8/16.
 * 通讯录
 */
@Service
public class AppdAddressBookService {

    @Autowired
    private OrganizationDepartmentRepository organizationDepartmentRepository;
    @Autowired
    private OrganizationJobsRepository organizationJobsRepository;
    @Autowired
    private SysUserDao sysUserDao;

    public List<AppdAddressBook> getList() {
        List<AppdAddressBook> appdAddressBooks = organizationDepartmentRepository.getListForApp();
        List<AppdAddressBook> listForApp = organizationJobsRepository.getListForApp();
        appdAddressBooks.addAll(listForApp);
        List<AppdAddressBook> listForApp1 = sysUserDao.getListForApp();
        appdAddressBooks.addAll(listForApp1);
        return appdAddressBooks;
    }
}
