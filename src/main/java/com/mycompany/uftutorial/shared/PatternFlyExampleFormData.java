package com.mycompany.uftutorial.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 * A JavaBean that can be bound automatically to the HTML form from the PatternFly example. Property names were chosen
 * to align with the id values in the sample HTML from PatternFly.
 * <p>
 * This class is annotated with {@code @Bindable} so it can be auto-bound to the form.
 * <p>
 * This class is annotated with {@code @Portable} so instances can be sent to and from the server via Errai Bus or Errai JAX-RS.
 */
@Portable
@Bindable
public class PatternFlyExampleFormData {

  private String textInput;
  private String textInput2;
  private String textInput3;
  private String textInput4;

  public String getTextInput() {
    return textInput;
  }
  public void setTextInput(String textInput) {
    this.textInput = textInput;
  }
  public String getTextInput2() {
    return textInput2;
  }
  public void setTextInput2(String textInput2) {
    this.textInput2 = textInput2;
  }
  public String getTextInput3() {
    return textInput3;
  }
  public void setTextInput3(String textInput3) {
    this.textInput3 = textInput3;
  }
  public String getTextInput4() {
    return textInput4;
  }
  public void setTextInput4(String textInput4) {
    this.textInput4 = textInput4;
  }

  @Override
  public String toString() {
    return "PatternFlyExampleFormData [textInput=" + textInput + ", textInput2=" + textInput2 + ", textInput3="
            + textInput3 + ", textInput4=" + textInput4 + "]";
  }
}
