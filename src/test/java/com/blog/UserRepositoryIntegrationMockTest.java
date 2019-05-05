package com.blog;

import com.blog.model.UserEntity;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import com.blog.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringRunner.class)
public class UserRepositoryIntegrationMockTest {

    @TestConfiguration
    static class UserRepositoryIntegrationMockTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        UserEntity alex = UserEntity.builder().username("Alex").email("alex@myblog.com").build();

        Mockito.when(userRepository.findByUsername(alex.getUsername())).thenReturn(alex);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "alex";
        UserEntity found = userService.findUserByUserName(name);

        assertEquals(found.getUsername(),name);
    }

}
