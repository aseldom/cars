package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernatePostRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final HibernatePostRepository postRepository = new HibernatePostRepository(new CrudRepository(sf));
    private final HibernateUserRepository userRepository = new HibernateUserRepository(new CrudRepository(sf));

    @AfterEach
    public void clearRepository() {
        for (var e : postRepository.findAll()) {
            postRepository.deleteById(e.getId());
        }
        for (var e : userRepository.findAllOrderById()) {
            userRepository.delete(e.getId());
        }
    }

    private static Post getPost(String description) {
        Post post = new Post();
        post.setDescription(description);
        post.setUser(getUser("slogin" + description));
        return post;
    }

    private static User getUser(String login) {
        User user = new User();
        user.setLogin(login);
        user.setPassword("password");
        return user;
    }

    @Test
    public void whenAddPostThenFindByIdAndGetThatPost() {
        Post post = getPost("Post 1");
        postRepository.add(post);
        var res = postRepository.findById(post.getId()).get();
        assertThat(res.getDescription()).isEqualTo(post.getDescription());
    }

    @Test
    public void whenUpdatePostThenGetUpdated() {
        Post post1 = getPost("Post 1");
        Post post2 = getPost("Post 2");
        postRepository.add(post1);
        post2.setId(post1.getId());
        postRepository.update(post2);
        Post res = postRepository.findById(post1.getId()).get();
        assertThat(res.getDescription()).isEqualTo(post2.getDescription());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Post post = getPost("Post 1");
        postRepository.add(post);
        assertThat(postRepository.findById(post.getId()).get()).isEqualTo(post);
        postRepository.deleteById(post.getId());
        Optional<Post> res = postRepository.findById(post.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoPostsFindAllThenGetTwoPosts() {
        Post post1 = getPost("Post 1");
        Post post2 = getPost("Post 2");
        postRepository.add(post1);
        postRepository.add(post2);
        var res = postRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(post1, post2);
    }

}