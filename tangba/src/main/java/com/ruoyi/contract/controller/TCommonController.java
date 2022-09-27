package com.ruoyi.contract.controller;


import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 登录用户信息Controller
 *
 */
@RestController
@RequestMapping("/contract/common")
public class TCommonController {

    @GetMapping("/userId")
    public Long getUserId(){
        System.out.println("################"+ SecurityUtils.getUserId());
        return SecurityUtils.getUserId();
    }


}
