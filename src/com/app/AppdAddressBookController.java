package com.app;

import com.common.vo.JsonResult;
import com.service.AppdAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 高宇飞 on 2017/8/16.
 * 通讯录
 */
@RestController
@RequestMapping("api/addressBook")
public class AppdAddressBookController {


    @Autowired
    private AppdAddressBookService addressBookService;

    @GetMapping(value = "getList")
    public JsonResult getList() {
        return new JsonResult(true, "请求成功", addressBookService.getList());
    }
}
