package io.github.javathought.winecellar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class CellarInformation   {
  
  private Float temparature = null;
  private Float hygrometry = null;
  private Integer availableLocation = null;

  public CellarInformation () {

  }

  public CellarInformation (Float temparature, Float hygrometry, Integer availableLocation) {
    this.temparature = temparature;
    this.hygrometry = hygrometry;
    this.availableLocation = availableLocation;
  }

    
  @JsonProperty("temparature")
  public Float getTemparature() {
    return temparature;
  }
  public void setTemparature(Float temparature) {
    this.temparature = temparature;
  }

    
  @JsonProperty("hygrometry")
  public Float getHygrometry() {
    return hygrometry;
  }
  public void setHygrometry(Float hygrometry) {
    this.hygrometry = hygrometry;
  }

    
  @JsonProperty("availableLocation")
  public Integer getAvailableLocation() {
    return availableLocation;
  }
  public void setAvailableLocation(Integer availableLocation) {
    this.availableLocation = availableLocation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CellarInformation cellarInformation = (CellarInformation) o;
    return Objects.equals(temparature, cellarInformation.temparature) &&
        Objects.equals(hygrometry, cellarInformation.hygrometry) &&
        Objects.equals(availableLocation, cellarInformation.availableLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(temparature, hygrometry, availableLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CellarInformation {\n");
    
    sb.append("    temparature: ").append(toIndentedString(temparature)).append("\n");
    sb.append("    hygrometry: ").append(toIndentedString(hygrometry)).append("\n");
    sb.append("    availableLocation: ").append(toIndentedString(availableLocation)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
