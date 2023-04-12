package com.example.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * HTML에서 이미지관련한 값들을 불러올때 외부경로의 데이터를 가져오기에 이곳에 설정,
     * folder에 해당하는 주소의 이미지 데이터는 /projectimg라는 경로로 시작하는 주소로 입력하면 가져올 수 있다.
     */
    @Value("${file.Upfolder}")
    private String folder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/projectimg/**") 	//리소스 등록 및 핸들러를 관리하는 객체인 ResourceHandlerRegistry를 통해 리소스 위치와 이 리소스와 매칭될 url을 등록합니다.
                .addResourceLocations(folder); 		 	//projectimg로 시작하는url로 요청을 할경우
    }

}
