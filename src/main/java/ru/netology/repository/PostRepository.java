package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  static AtomicLong id = new AtomicLong(0);
  private static List<Post> postList = new CopyOnWriteArrayList<>();
  public List<Post> all() {
    return Collections.unmodifiableList(postList);
  }

  public Optional<Post> getById(long id) {
    return postList.stream()
            .filter(post -> post.getId() == id)
            .findFirst();
  }

  public Post save(Post post) {
    final var postId = post.getId();
    if (postId == 0) {
      post.setId(id.incrementAndGet());
      postList.add(post);
    }
    return post;
  }

  public void removeById(long id) {
    postList.removeIf(post -> post.getId() == id);
  }
}
