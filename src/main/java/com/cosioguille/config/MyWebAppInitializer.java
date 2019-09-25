package com.cosioguille.config;
 
import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[] { DatasourceConfig.class};
   }
 
   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] { WebConfig.class};
   }
 
   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }
   
   @Override
   protected Filter[] getServletFilters() {
       Filter [] singleton = { new CORSFilter() };
       return singleton;
   }
}