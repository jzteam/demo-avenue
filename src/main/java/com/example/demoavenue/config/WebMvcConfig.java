package com.example.demoavenue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfig {
    
    @Bean
    public TestPerson person(){
        return new TestPerson("person");
    }

    @Bean
    public TestSon son(){
        return new TestSon("son");
    }
    
    
    public static class TestPerson {
        private String name;

        public TestPerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    
    public static class TestSon extends TestPerson{
        public TestSon(String name) {
            super(name);
        }
    }

}
