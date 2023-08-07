//package com.example.untiled1.configuration;
//
//import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//import java.util.Properties;
//
//@Configuration
//public class MessageSourceConfig {
//    @Bean(name = "messagesProperties")
//    public Properties yamlProperties() {
//        var bean = new YamlPropertiesFactoryBean();
//        bean.setResources(new ClassPathResource("message.yml"));
//        return bean.getObject();
//    }
//
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setCommonMessages(yamlProperties());
//        messageSource.setBasename("classpath:message.yml");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean mvcValidator(MessageSource messageSource) {
//        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
//        factory.setValidationMessageSource(messageSource);
//        return factory;
//    }
//}
