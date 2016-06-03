package cn.ctyun.standdemo.rest;

import cn.ctyun.core.base.rest.RESTAnnotate;
import cn.ctyun.core.base.rest.annotate.RESTAPI;
import cn.ctyun.core.base.rest.annotate.StringParam;
import cn.ctyun.core.base.rest.handler.REST.BasicRESTCmdlet;
import cn.ctyun.core.base.rest.handler.REST.RequestSource;
import cn.ctyun.core.base.rest.result.AbstractRESTResult;
import cn.ctyun.core.base.rest.result.JsonRESTResult;
import cn.ctyun.core.base.rest.result.RESTStatusCode;
import org.eclipse.jetty.http.HttpMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import cn.ctyun.standdemo.model.User;
import cn.ctyun.standdemo.service.UserServiceI;

import javax.annotation.Resource;

/**
 * 接受get和post请求，返回json、jsp。
 *
 */
@Controller
@RESTAnnotate(URL = "/api/getUserById", Methods = HttpMethods.POST)
public class UserController  extends BasicRESTCmdlet {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

    @RESTAPI
    public AbstractRESTResult getList(@StringParam(value = "id",required = true,source = RequestSource.RequestParam)String id)
    {
        JsonRESTResult result = new JsonRESTResult();
        try
        {
            User u = userService.getUserById(id);
            result.setReturnObj(u);
            result.setStatusCode(RESTStatusCode.SUCCESS);
            return   result;
        }catch(Exception e)
        {
            result.setStatusCode(RESTStatusCode.ERROR);
            logger.error("Internal error, querying personal consoleUser failed: {}",e);
            return  result;
        }
    }

}
