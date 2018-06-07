package com.robot.common.utils;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
* @ClassName: AppUtil 
* @Description: TODO(应用工具类) 
* @author Allen
 */
public class AppUtil  implements ApplicationContextAware
{
  private static ApplicationContext applicationContext;
  private static ServletContext servletContext;
  
  public static void init(ServletContext _servletContext)
  {
    servletContext = _servletContext;
  }
  
  public void setApplicationContext(ApplicationContext contex)
    throws BeansException
  {
    applicationContext = contex;
  }
  
  public static ApplicationContext getContext()
  {
    return applicationContext;
  }
  
  public static ServletContext getServletContext()    throws Exception
  {
    return servletContext;
  }
  /**
   * 
  * getBean 
  * TODO(根据class获取注入对像) 
  * @param cls
  * @return    设定文件
   */
  public static Object getBean(Class cls)
  {
    return applicationContext.getBean(cls);
  }
  /**
   * 
  * getBean 
  * TODO(根据beanID获取注入对像) 
  * @param beanId
  * @return    设定文件
   */
  public static Object getBean(String beanId)
  {
    return applicationContext.getBean(beanId);
  }
  
  public static String getAppAbsolutePath()
  {
    return servletContext.getRealPath("/");
  }
  
  public static String getRealPath(String path)
  {
    return servletContext.getRealPath(path);
  }
  
  
  public static String getClasspath()
  {
    String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String rootPath = "";
    if ("\\".equals(File.separator))
    {
      rootPath = classPath.substring(1);
      rootPath = rootPath.replace("/", "\\");
    }
    if ("/".equals(File.separator))
    {
      rootPath = classPath.substring(1);
      rootPath = rootPath.replace("\\", "/");
    }
    return rootPath;
  }
  
  public static void main(String[] args)
  { 
    System.out.println(getClasspath());
  }
}

