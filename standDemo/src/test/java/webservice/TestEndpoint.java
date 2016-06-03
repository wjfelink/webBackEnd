package webservice;

import cn.ctyun.webservice.service.HelloWorldImpl;
import cn.ctyun.webservice.service.IHelloWorld;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import javax.xml.ws.Endpoint;

public class TestEndpoint extends TestCase {
    
    private static final String ADDRESS = "http://localhost:8181/api/services";
    protected void setUp() throws Exception {
        super.setUp();
        ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
        System.out.println("Starting Server");  
        HelloWorldImpl demo = new HelloWorldImpl();
        
        Endpoint.publish(ADDRESS, demo);
        System.out.println("Start success");
    }

    @Test
    public void testSayHello(){
        
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(IHelloWorld.class);
        factory.setAddress(ADDRESS);
        IHelloWorld client = (IHelloWorld)factory.create();
        Assert.assertEquals(client.sayHello("foo"), "foosay:Hello World!");
    }
}