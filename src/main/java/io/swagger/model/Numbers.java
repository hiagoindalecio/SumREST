package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The numbers object.
 */
@Schema(description = "The numbers object.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T21:52:52.495Z[GMT]")


public class Numbers   {
  @JsonProperty("firstNumber")
  private Double firstNumber = null;

  @JsonProperty("secondNumber")
  private Double secondNumber = null;

  public Numbers firstNumber(Double firstNumber) {
    this.firstNumber = firstNumber;
    return this;
  }

  /**
   * The first number attribute
   * @return fisrtNumber
   **/
  @Schema(description = "The first number attribute")
  
    @Valid
    public Double getFirstNumber() {
    return firstNumber;
  }

  public void setFirstNumber(Double fisrtNumber) {
    this.firstNumber = fisrtNumber;
  }

  public Numbers secondNumber(Double secondNumber) {
    this.secondNumber = secondNumber;
    return this;
  }

  /**
   * The second number attribute
   * @return secondNumber
   **/
  @Schema(required = true, description = "The second number attribute")
      @NotNull

    @Valid
    public Double getSecondNumber() {
    return secondNumber;
  }

  public void setSecondNumber(Double secondNumber) {
    this.secondNumber = secondNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Numbers numbers = (Numbers) o;
    return Objects.equals(this.firstNumber, numbers.firstNumber) &&
        Objects.equals(this.secondNumber, numbers.secondNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstNumber, secondNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Numbers {\n");
    
    sb.append("    fisrtNumber: ").append(toIndentedString(firstNumber)).append("\n");
    sb.append("    secondNumber: ").append(toIndentedString(secondNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
