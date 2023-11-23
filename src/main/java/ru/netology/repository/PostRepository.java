package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
@Repository
public class PostRepository {
    private AtomicLong id = new AtomicLong(0);

    private Map<Long, Post> postMap = new ConcurrentHashMap<>();

    public List<Post> all() {
        return Collections.unmodifiableList(new ArrayList<>(
                postMap.values()
                .stream()
                .filter(post -> !post.getIsRemoved())
                .collect(Collectors.toList())));
    }

    public Post getById(long id) {
        Post post = postMap.get(id);
        if (post == null || post.getIsRemoved()) {
            throw new NotFoundException();
        }
        return post;
    }

    public Post save(Post post) {
        final var postId = post.getId();
        if (postId != 0) {
            Post existingPost = postMap.get(postId);
            if (existingPost == null || existingPost.getIsRemoved()) {
                throw new NotFoundException();
            }
        } else {
            post.setId(id.incrementAndGet());
        }
        postMap.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        postMap.get(id).setIsRemoved(true);
    }
}
