package cn.ctyun.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IHelloWorld {

    @WebMethod
	public String sayHello(@WebParam(name = "name") String name);

}
