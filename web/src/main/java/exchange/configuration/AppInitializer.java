package exchange.configuration;


import org.service.spring.config.ConfigService;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

public class AppInitializer implements WebApplicationInitializer {
	@Override
    public void onStartup(ServletContext container) throws ServletException {
 
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        ctx.register(ConfigService.class); 
        ctx.register(SecurityConfig.class);
        container.addListener(new ContextLoaderListener(ctx)); 
        ctx.setServletContext(container);
        Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
 
      
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }


}