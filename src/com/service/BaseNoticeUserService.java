package com.service;

import com.app.dto.BaseNoticeUserDTO;
import com.common.service.BaseService;
import com.entity.BaseNoticeUser;
import com.repositories.BaseNoticeUserRepository;
import com.searchVO.BaseNoticeUserSearchVO;
import com.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 1553280431@qq.com on 2017/6/21.
 * 通知
 */
@Service
public class BaseNoticeUserService implements BaseService<BaseNoticeUser, String> {

    @Autowired
    private BaseNoticeUserRepository baseNoticeUserRepository;

    @Override
    public <S extends BaseNoticeUser> S save(S entity) {
        return null;
    }

    @Override
    public BaseNoticeUser findOne(String primaryKey) {
        return null;
    }

    @Override
    public List<BaseNoticeUser> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(String primaryKey) {
    }

    public void save(List<SysUser> sysUserList, String noticeId) {
        if (sysUserList != null && sysUserList.size() > 0) {
            List<BaseNoticeUser> baseNoticeUsers = new ArrayList<>();
            for (SysUser sysUser : sysUserList) {
                BaseNoticeUser noticeUser = new BaseNoticeUser();
                noticeUser.setIsRead(0);
                noticeUser.setNoticeId(noticeId);
                noticeUser.setUserId(sysUser.getId());
                noticeUser.setRealName(sysUser.getRealname());
                noticeUser.setDepartmentId(sysUser.getDepartmentId());
                noticeUser.setJobsId(sysUser.getJobsId());
                baseNoticeUsers.add(noticeUser);

            }
            baseNoticeUserRepository.save(baseNoticeUsers);
        }
    }

    public List<BaseNoticeUser> getListByNoticeId(BaseNoticeUserSearchVO searchVO) {
        return baseNoticeUserRepository.getListByNoticeId(searchVO);
    }

    public List<BaseNoticeUserDTO> getListByUserId(BaseNoticeUserSearchVO searchVO) {
        return baseNoticeUserRepository.getListByUserId(searchVO);
    }

    public void deleteByNoticeId(String noticeId) {
        baseNoticeUserRepository.deleteByNoticeId(noticeId);
    }

    public void updateReadDateById(Integer id) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        baseNoticeUserRepository.setReadDateById(format, 1, id);
    }
}
