package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;
  private final Gson gson = new Gson();

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public List<Post> all() throws IOException {
    return service.all();
  }
@GetMapping("/{id}")
  public Post getById(@PathVariable long id) throws IOException {
    return service.getById(id);
  }

  @PostMapping
  public Post save(@RequestBody Post post) throws IOException {
    return service.save(post);
  }
  @DeleteMapping("/{id}")
  public void removeById(long id) {
    service.removeById(id);
  }

  public void writeResponse(HttpServletResponse response, Object data) throws IOException {
    response.setContentType(APPLICATION_JSON);
    response.getWriter().print(gson.toJson(data));
  }
}
