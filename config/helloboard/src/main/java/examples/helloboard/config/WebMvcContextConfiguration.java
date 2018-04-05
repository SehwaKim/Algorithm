package examples.helloboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc // 얘가 설정되면 웹어플리케이션에 대한 설정은 대부분 자동으로 된다
@ComponentScan(basePackages = { "examples.helloboard.controller" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 
    // default servlet handler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
    }
    //위의 3가지는 spring mvc에 대한 설정을 내가 해준것임
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver; //지금 이 안의 내용이 전략을 짜논것? 이러한 전략으로 동작하도록 해라.
        //사용할 뷰가 결정됨. 해당 경로의 뷰를 사용하기로 함
        //그 jsp가 사용되면서 화면에 보여진다

        //똑같은 path로 요청햇다하더라도 요청타입이 다르면 다른 뷰 리졸버가 호출된다
    }
    // InternalResourceViewResolver --> 얘는 왜 인스턴스 생성만하고 아무데서도 getBean을 안하나?
    // 뷰리졸버라는 '객체들'이 있으면 디스패처 서블릿이 그 객체들을 내부적으로 이용한다
    // 내가 사용하는 객체가 아니라 디스패처 서블릿을 위해서 생성한 객체다
    // 디스패처 서블릿이 어떻게 동작하는지 알아야함!!!

}
