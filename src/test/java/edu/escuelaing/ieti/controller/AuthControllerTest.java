package edu.escuelaing.ieti.controller;

import edu.escuelaing.ieti.controller.auth.AuthController;
import edu.escuelaing.ieti.controller.auth.LoginDto;
import edu.escuelaing.ieti.controller.user.UserDto;
import edu.escuelaing.ieti.controller.user.UsersController;
import edu.escuelaing.ieti.repository.user.User;
import edu.escuelaing.ieti.service.user.UsersService;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class AuthControllerTest {
    final String BASE_URL = "/v1/auth";

    @Autowired
    private AuthController controller;

    @MockBean
    private UsersService usersService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testAuthentication() throws Exception {
        UserDto userDto = new UserDto("Juan", "Sanchez", "juan@mail.com", "pass123");
        User user = new User(userDto);
        LoginDto loginDto = new LoginDto("juan@mail.com", "pass123");
        when(usersService.findByEmail("juan@mail.com")).thenReturn(user);

        String json = "{\"email\":\"juan@mail.com\",\"password\":\"pass123\"}";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

    }


}
