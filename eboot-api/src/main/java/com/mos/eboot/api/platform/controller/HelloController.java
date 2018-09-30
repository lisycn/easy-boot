package com.mos.eboot.api.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mos.eboot.tools.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author shilei
 * @Date 2017/6/9.
 */
@Api(tags="hello类")
@Controller
public class HelloController extends BaseController{
	@ApiOperation(value="/hello",notes="hello接口")
    @RequestMapping(value="/hello",method=RequestMethod.GET)
    @ResponseBody
    public String heelo(){
        return "123";
    }
	@ApiOperation(value="/users",notes="users接口")
    @RequestMapping(value="/users",method=RequestMethod.GET)
    public String getUsers() {
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}
