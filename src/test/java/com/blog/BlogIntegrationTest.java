package com.blog;

import com.blog.model.CommentEntity;
import com.blog.model.PostEntity;
import com.blog.model.UserEntity;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@DataJpaTest
public class BlogIntegrationTest {

	private static UserEntity alex;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void test1_whenFindByName_thenReturnUser() {
		alex = UserEntity.builder().username("Alex").email("alex@myblog.com").roleId(1L).build();
		entityManager.persist(alex);
		entityManager.flush();
		// when
		UserEntity found = userRepository.findByUsernameAndEmail(alex.getUsername(),alex.getEmail());

		// then
		assertEquals(found.getUsername(),alex.getUsername());
	}

	@Test
	public void test2_whenFindByUserId_thenReturnPost() {
		// given
		PostEntity firstPost = PostEntity.builder().title("This is the title").postText("This is just a test.").userId(alex.getId()).build();

		entityManager.persist(firstPost);
		entityManager.flush();

		// when
		List<PostEntity> found = postRepository.findByUserIdOrderByModifiedAt(alex.getId());

		// then
		assertEquals(found.stream().findFirst().get().getUserId(),alex.getId());
	}

	@Test
	public void test3_whenFindByUserId_thenReturnComment() {

		// given
		List<PostEntity> found = postRepository.findByUserIdOrderByModifiedAt(alex.getId());

		CommentEntity firstComment = CommentEntity.builder().commentText("This comment is awesome").userId(alex.getId()).postId(found.stream().findFirst().get().getId()).build();

		entityManager.persist(firstComment);
		entityManager.flush();

		// when
		List<CommentEntity> foundComment = commentRepository.findByUserIdOrderByModifiedAt(alex.getId());

		// then
		assertEquals(foundComment.stream().findFirst().get().getUserId(),alex.getId());
	}

	@Test
	public void test4_whenFind_thenDeleteAll() {

		// when
		List<CommentEntity> foundComment = commentRepository.findByUserIdOrderByModifiedAt(alex.getId());
		commentRepository.deleteById(foundComment.stream().findFirst().get().getId());

		List<PostEntity> foundPost = postRepository.findByUserIdOrderByModifiedAt(alex.getId());
		postRepository.deleteById(foundPost.stream().findFirst().get().getId());

		UserEntity found = userRepository.findByUsername(alex.getUsername());
		userRepository.deleteById(found.getId());

		// then
		assertNull(userRepository.findByUsername(alex.getUsername()));
		assertTrue(postRepository.findByUserIdOrderByModifiedAt(alex.getId()).isEmpty());
		assertTrue(commentRepository.findByUserIdOrderByModifiedAt(alex.getId()).isEmpty());
	}


}
