package com.weponsystem.weaponsystem.configuration;

import com.weponsystem.weaponsystem.model.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CommandConfiguration {

    @Bean
    public List<Command>getCommand(){
        return  new ArrayList<Command>();
    }


}
