package com.weponsystem.weaponsystem.controllers;

import com.weponsystem.weaponsystem.model.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


/**
 * I was unable to complete all the test cases in the given time as
 * i was unable to figure out how to inject the command list bean and mock it as well
 */


@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(CommandController.class)
public class CommandControllerTest {


    @TestConfiguration
    static class TestConfig {

        @Bean
        public List<Command> command() {
            return new ArrayList<Command>();
        }

    }


    @Autowired
    private List<Command> commandList;
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void clearBean() throws Exception {
        MockitoAnnotations.initMocks(this);

        commandList.clear();

//        String commands = "[\\r\\n     {\\r\\n       \\r\\n        \\\"weaponSystem\\\": \\\"Millennium Falcon\\\",\\r\\n        \\\"battleship\\\": \\\"heavy laser cannons\\\",\\r\\n        \\\"target\\\": \\\"Imperial Star Destroyer\\\",\\r\\n        \\\"quantity\\\": 10000,\\r\\n        \\\"rate\\\": 100\\r\\n    },\\r\\n    {\\r\\n        \\r\\n        \\\"weaponSystem\\\": \\\"Millennium Falcon\\\",\\r\\n        \\\"battleship\\\": \\\"quad-bolt cannons\\\",\\r\\n        \\\"target\\\": \\\"Death Star\\\",\\r\\n        \\\"quantity\\\": 1000,\\r\\n        \\\"rate\\\": 700\\r\\n    },\\r\\n    {\\r\\n        \\r\\n        \\\"weaponSystem\\\": \\\"Tantive IV\\\",\\r\\n        \\\"battleship\\\": \\\"anti-ship turbolasers\\\",\\r\\n        \\\"target\\\": \\\"Razor Crest\\\",\\r\\n        \\\"quantity\\\": 1000,\\r\\n        \\\"rate\\\": 10\\r\\n    },\\r\\n    {\\r\\n        \\r\\n        \\\"weaponSystem\\\": \\\"Tantive IV\\\",\\r\\n        \\\"battleship\\\": \\\"tractor beam projector\\\",\\r\\n        \\\"target\\\": \\\"Death Star\\\",\\r\\n        \\\"quantity\\\": 2000,\\r\\n        \\\"rate\\\": 100\\r\\n    },\\r\\n\\t {\\r\\n       \\r\\n        \\\"weaponSystem\\\": \\\"Home One\\\",\\r\\n        \\\"battleship\\\": \\\"heavy laser cannons\\\",\\r\\n        \\\"target\\\": \\\"Executor\\\",\\r\\n        \\\"quantity\\\": 1000,\\r\\n        \\\"rate\\\": 30\\r\\n    },\\r\\n    {\\r\\n        \\r\\n        \\\"weaponSystem\\\": \\\"Home One\\\",\\r\\n        \\\"battleship\\\": \\\"quad-bolt cannons\\\",\\r\\n        \\\"target\\\": \\\"Razor Crest\\\",\\r\\n        \\\"quantity\\\": 1000,\\r\\n        \\\"rate\\\": 50\\r\\n    },\\r\\n    {\\r\\n        \\r\\n        \\\"weaponSystem\\\": \\\"Tantive IV\\\",\\r\\n        \\\"battleship\\\": \\\"anti-ship turbolasers\\\",\\r\\n        \\\"target\\\": \\\"Executor\\\",\\r\\n        \\\"quantity\\\": 1000,\\r\\n        \\\"rate\\\": 700\\r\\n    },\\r\\n    {\\r\\n       \\r\\n        \\\"weaponSystem\\\": \\\"Home One\\\",\\r\\n        \\\"battleship\\\": \\\"quad-bolt cannons\\\",\\r\\n        \\\"target\\\": \\\"Executor\\\",\\r\\n        \\\"quantity\\\": 1000,\\r\\n        \\\"rate\\\": 10\\r\\n    },\\r\\n\\t    {\\r\\n       \\r\\n        \\\"weaponSystem\\\": \\\"Millennium Falcon\\\",\\r\\n        \\\"battleship\\\": \\\"heavy laser cannons\\\",\\r\\n        \\\"target\\\": \\\"Imperial Star Destroyer\\\",\\r\\n        \\\"quantity\\\": 2000,\\r\\n        \\\"rate\\\": 100\\r\\n    },\\r\\n\\t    {\\r\\n       \\r\\n        \\\"weaponSystem\\\": \\\"Home One\\\",\\r\\n        \\\"battleship\\\": \\\"quad-bolt cannons\\\",\\r\\n        \\\"target\\\": \\\"Razor Crest\\\",\\r\\n        \\\"quantity\\\": 1000,\\r\\n        \\\"rate\\\": 30\\r\\n    }\\r\\n   \\r\\n]";
//
//        postData(commands);
    }

    @Test
    public void getALLCommands() throws Exception {
        // when
        MockHttpServletResponse response = mvc.perform(
                get("/commands")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString().isEmpty());
    }


    private void postData(String data) throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/addCommands.json").contentType(MediaType.APPLICATION_JSON).content(data))
                .andReturn().getResponse();
    }


}