package cn.ctyun.webservice.service;


import javax.jws.WebMethod;

public class HelloWorldImpl implements IHelloWorld{

    @Override
	public String sayHello(String name) {
        return name + "say:Hello World!";
	}
}
