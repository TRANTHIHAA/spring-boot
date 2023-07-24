package com.example.untiled1.global.utils;

import com.example.untiled1.global.component.ApplicationProperties;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtils  implements ApplicationContextAware {
    private static final Logger log4j = LoggerFactory.getLogger(BeanUtils.class);
    private static ApplicationContext context;
    private static ApplicationProperties objApplicationProperties;
    public BeanUtils() {
    }

    public static ApplicationContext getContext() {
        return context;
    }
    public static void setContext(ApplicationContext context) {
        BeanUtils.context = context;
    }
    public void setApplicationContext(ApplicationContext context) {
        setContext(context);
    }
    public static <T> T getBean(Class<T> beanClass) {
        if (getContext() == null) {
            throw new ResourceNotFoundException(BeanUtils.class.getName(), ApplicationContext.class.getName());
        } else {
            return getContext().getBean(beanClass);
        }
    }
    public static <T> T getBean(String beanClass) {
        if (getContext() == null) {
            throw new ResourceNotFoundException(BeanUtils.class.getName(), ApplicationContext.class.getName());
        } else {
            return (T) getContext().getBean(beanClass);
        }
    }

    public static <T> T getBean(String beanClass, T clazz) {
        if (clazz != null) {
            return clazz;
        } else {
            log4j.info("getBean by name: [{}]", beanClass);
            return getBean(beanClass);
        }
    }
    public static ApplicationProperties getApplicationProperties() {
        if (objApplicationProperties == null) {
            objApplicationProperties = (ApplicationProperties)getBean(ApplicationProperties.class);
        }

        return objApplicationProperties;
    }
}
