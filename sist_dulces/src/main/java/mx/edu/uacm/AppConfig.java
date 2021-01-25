package mx.edu.uacm;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/catalogo").setViewName("catalogo");
        registry.addViewController("/iniciarSesion").setViewName("login");
        registry.addViewController("/tienda").setViewName("tienda");
    }
}
