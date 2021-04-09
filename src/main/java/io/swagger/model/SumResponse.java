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
 * The sum response object.
 */
@Schema(description = "The sum response object.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T21:52:52.495Z[GMT]")


public class SumResponse   {
  @JsonProperty("description")
  private String description = null;

  @JsonProperty("result")
  private BigDecimal result = null;

  public SumResponse description(String description) {
    this.description = description;
    return this;
  }

  /**
   * The response description attribute
   * @return description
   **/
  @Schema(description = "The response description attribute")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SumResponse result(BigDecimal result) {
    this.result = result;
    return this;
  }

  /**
   * The response number attribute
   * @return result
   **/
  @Schema(description = "The response number attribute")
  
    @Valid
    public BigDecimal getResult() {
    return result;
  }

  public void setResult(BigDecimal result) {
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
    SumResponse sumResponse = (SumResponse) o;
    return Objects.equals(this.description, sumResponse.description) &&
        Objects.equals(this.result, sumResponse.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SumResponse {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
}
