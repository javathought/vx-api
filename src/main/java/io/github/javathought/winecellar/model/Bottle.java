package io.github.javathought.winecellar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bottle   {

  private Long id = null;
  private String name = null;
  private String pays;


  public enum VarietyEnum {
    CHARDONNAY("Chardonnay"),
    GEW_RSTRAMINER("Gewürstraminer"),
    SAUVIGNON_BLANC("Sauvignon blanc");

    private String value;

    VarietyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private VarietyEnum variety = null;
  private Integer year = null;
  private Integer quantity = null;

  public Bottle () {

  }

  public Bottle (Long id, String name, VarietyEnum variety, Integer year, Integer quantity) {
    this.id = id;
    this.name = name;
    this.variety = variety;
    this.year = year;
    this.quantity = quantity;
  }

    
  @JsonProperty("id")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

    
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

    
  @JsonProperty("variety")
  public VarietyEnum getVariety() {
    return variety;
  }
  public void setVariety(VarietyEnum variety) {
    this.variety = variety;
  }

    
  @JsonProperty("year")
  public Integer getYear() {
    return year;
  }
  public void setYear(Integer year) {
    this.year = year;
  }

    
  @JsonProperty("quantity")
  public Integer getQuantity() {
    return quantity;
  }
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getPays() {
    return pays;
  }

  public void setPays(String pays) {
    this.pays = pays;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bottle bottle = (Bottle) o;
    return Objects.equals(id, bottle.id) &&
        Objects.equals(name, bottle.name) &&
        Objects.equals(variety, bottle.variety) &&
        Objects.equals(year, bottle.year) &&
        Objects.equals(quantity, bottle.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, variety, year, quantity);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
            .append("id", id)
            .append("name", name)
            .append("pays", pays)
            .append("variety", variety)
            .append("year", year)
            .append("quantity", quantity)
            .toString();
  }

}
