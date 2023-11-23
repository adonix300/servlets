package ru.netology.model;

public class Post {
  private long id;
  private String content;
  private boolean isRemoved;
  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setIsRemoved(boolean isRemoved) {
    this.isRemoved = isRemoved;
  }

  public boolean getIsRemoved () {
    return isRemoved;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
