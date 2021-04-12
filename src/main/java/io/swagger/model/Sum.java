package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Numbers;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The array of numbers
 */
@Schema(description = "The array of numbers")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T21:52:52.495Z[GMT]")

public class Sum extends Numbers  {
  @JsonProperty("result")
  private Double result = null;

  public Sum result(Double result) {
    this.result = result;
    return this;
  }
  
  public Sum (Numbers numbers, Double result) {
	if(this != null) {
	  this.setFirstNumber(numbers.getFirstNumber());
	  this.setSecondNumber(numbers.getSecondNumber());
	  this.setResult(result);
	}
  }
  
  public Sum CalcResult () {
    if(this != null) {
	  this.setResult(this.getFirstNumber() + this.getSecondNumber());
	}
    return this;
  }

  /**
   * The sum of the two numbers attribute
   * @return result
   **/
  @Schema(description = "The sum of the two numbers attribute")
  
    @Valid
    public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sum sum = (Sum) o;
    return Objects.equals(this.result, sum.result) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sum {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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
  
  public static class Builder {
	  
	private final Double firstNumber;
	private final Double secondNumber;
	private final Double result;
	
	public Builder(Double first, Double sec, Double res) {
	  this.firstNumber = first;
	  this.secondNumber = sec;
	  this.result = res;
	}
	
	public Sum build() {
		Numbers num = new Numbers();
		num.setFirstNumber(firstNumber);
		num.setSecondNumber(secondNumber);
		return new Sum(num, this.result);
	}
  }
}
