package yatospace.worker.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DatabaseListener implements ServletContextListener {
    public DatabaseListener() {}
    
    public void contextDestroyed(ServletContextEvent sce)    { }
    public void contextInitialized(ServletContextEvent sce)  { }
}
