package com.example.proyectospring;

import com.example.proyectospring.Repositories.UserRepository;
import com.example.proyectospring.Controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProyectoSpringApplicationTests {

    @Autowired
    MockMvc mvc;
    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
        assert userRepository.count() == 1;
    }


    @Test
    void listTest() throws Exception {
        mvc.perform(get("/users/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("javi"))
                .andExpect(jsonPath("$[0].username").value("bartu"))
                .andExpect(jsonPath("$[0].email").value("javi@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("123"));
    }


    @Test
    void creationTest() throws Exception {
        // HACK: stores repository count (for execution alone)
        long usersCount = userRepository.count();
        // create test prisoner
        String testUser = "{\"name\": \"lewis\", \"username\": \"liwi\", \"email\": \"aucom\", \"password\" : \"12345\"}";
        // method post on url /prisoners/
        mvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUser))
                .andExpect(status().isOk()) // test result
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].name").value("lewis"))
                .andExpect(jsonPath("$[1].username").value("laaa"))
                .andExpect(jsonPath("$[1].email").value("aucom"))
                .andExpect(jsonPath("$[1].password").value("12345"));
        // test number of prisoners is correct (+1)
        assert userRepository.count() == usersCount + 1;
    }
    @Test
    void detailTest() throws Exception {
        // get test prisoner (method get on url /prisoners/1/)
        mvc.perform(get("/users/1/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("javi"))
                .andExpect(jsonPath("$.username").value("bartu"))
                .andExpect(jsonPath("$.email").value("javi@gmail.com"))
                .andExpect(jsonPath("$.password").value("123"));
        // test result
    }
    @Test
    void updateTest() throws Exception {
        // get test prisoner (method get on url /prisoners/1/)
        // create test prisoner
        String testPrisoner = "{\"name\": \"lewis\", \"username\": \"liwi\", \"email\": \"aucom\", \"password\" : \"12345\"}";
        // modify attributes
        // method put on url /prisoners/1/
        mvc.perform(put("/prisoners/2/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testPrisoner))
                .andExpect(status().isOk()) // test result
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("lewis"))
                .andExpect(jsonPath("$.username").value("liwi"))
                .andExpect(jsonPath("$.email").value("aucom"))
                .andExpect(jsonPath("$.password").value("12345"));
        // test result
    }


    @Test
    void deleteTest(){

    }

}
