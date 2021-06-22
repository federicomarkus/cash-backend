package com.cash.app.user.repository;

import com.cash.app.user.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void test() {
        User user = userRepository.save(User.builder()
                .email("federicomarkus@gmail.com")
                .firstName("Federico")
                .lastName("Markus")
                .build());
        //
        User result = userRepository.getById(user.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result, user);
        userRepository.deleteById(user.getId());
        //
        Optional<User> result2 = userRepository.findById(user.getId());
        Assert.assertTrue(result2.isEmpty());
    }

}
