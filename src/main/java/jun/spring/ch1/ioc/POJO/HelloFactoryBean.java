package jun.spring.ch1.ioc.POJO;

import org.springframework.beans.factory.FactoryBean;

public class HelloFactoryBean implements FactoryBean<Hello> {
    @Override
    public Hello getObject() throws Exception {
        return HelloFactory.createHello();
    }

    @Override
    public Class<?> getObjectType() {
        return Hello.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
