package com.spranga.architecture2017.data.model;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by nietzsche on 02/04/17.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class Post {

  public int userId;
  public int id;
  public String title;
  public String body;
}
