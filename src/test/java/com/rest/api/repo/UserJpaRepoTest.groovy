package com.rest.api.repo

import com.rest.api.entity.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@DataJpaTest
class UserJpaRepoTest {

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void whenFindByUid_thenReturnUser(){
        String uid = "chae@gmail.com";
        String name = "chae";

        //given
        userJpaRepo.save(User.builder()
                .uid(uid)
                .password(passwordEncoder.encode("1234"))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        //when
        Optional<User> user = userJpaRepo.findByUid(uid);

        //then
        assertNotNull(user); //user 객체가 null이 아닌지 체크
        assertTrue(user.isPresent()); //user 객체가 존재여부 true/false 체크
        assertEquals(user.get().getName(), name);//user 객체의 name과 name 변수 값이 같은지 체크
        assertThat(user.get().getName(), is(name)); // user 객체의 name과 name 변수 값이 같은지 체크
    }
}
