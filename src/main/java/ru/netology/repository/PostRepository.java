package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private AtomicLong id = new AtomicLong(0);

    private Map<Long, Post> postMap = new ConcurrentHashMap<>();

    public List<Post> all() {
        return Collections.unmodifiableList(new ArrayList<>(postMap.values()));
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postMap.get(id));
    }

    public Post save(Post post) {
        final var postId = post.getId();
        if (postId == 0) {
            post.setId(id.incrementAndGet());
        }
        postMap.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        postMap.remove(id);
    }
}
