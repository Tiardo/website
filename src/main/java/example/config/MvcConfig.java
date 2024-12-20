package example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration


    public class MvcConfig implements WebMvcConfigurer {



        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login");
            registry.addViewController("/news").setViewName("news");
            registry.addViewController("/lk").setViewName("lk");
            registry.addViewController("/LKabinet").setViewName("LKabinet");
            registry.addViewController("/static").setViewName("static");
            registry.addViewController("/registration").setViewName("registration");
            registry.addViewController("/admin").setViewName("admin");
            registry.addViewController("/index").setViewName("index");
            registry.addViewController("/videoStream").setViewName("videoStream");
            registry.addViewController("/redirect").setViewName("redirect");
            registry.addViewController("/videos").setViewName("videos");
            registry.addViewController("/video").setViewName("video");
            registry.addViewController("/videoList").setViewName("videoList");
        }
    }


