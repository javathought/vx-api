package io.github.javathought.winecellar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    return new ToStringBuilder(this)
            .append("temparature", temparature)
            .append("hygrometry", hygrometry)
            .append("availableLocation", availableLocation)
            .toString();
  }

}
