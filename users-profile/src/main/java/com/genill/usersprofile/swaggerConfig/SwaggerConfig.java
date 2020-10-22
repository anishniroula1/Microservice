package com.genill.usersprofile.swaggerConfig;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.genill")).build()
                .apiInfo(apiDetails());
        /*return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/genill/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.genill"))
                .build()
                .apiInfo(apiDetails());*/
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Address Book API",
                "Sample API For Genill.com",
                "1.0",
                "Free To use",
                new springfox.documentation.service.Contact("Anish Niroula",
                        "https://genill.com", "anishniroula1@gmail.com"),
                "API License",
                "https://genill.com",
                Collections.emptyList());
    }

}
