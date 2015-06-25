package com.mycompany.uftutorial.shared;

/**
 * Model class representing a user's mood.
 */
public class Mood {

  private final String text;

  public Mood(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  @Override
  public String toString() {
    return text;
  }
}
