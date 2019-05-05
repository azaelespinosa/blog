package com.blog;

import com.blog.model.UserEntity;
import com.blog.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	@Ignore
	public void whenFindByName_thenReturnEmployee() {
		// given
		UserEntity alex = UserEntity.builder().username("Alex").email("alex@myblog.com").build();
		entityManager.persist(alex);
		entityManager.flush();

		// when
		UserEntity found = userRepository.findByUsernameAndEmail(alex.getUsername(),alex.getEmail());

		// then
		assertEquals(found.getUsername(),alex.getUsername());
	}


}
