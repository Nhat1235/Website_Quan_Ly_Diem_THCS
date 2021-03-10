package com.example.demo.helper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutorials")
public class Tutorial {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private String published;

  public Tutorial() {

  }

  public String getId() {
    return id;
  }

  public void setId(String string) {
    this.id = string;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String isPublished() {
    return published;
  }

  public void setPublished(String isPublished) {
    this.published = isPublished;
  }


}
