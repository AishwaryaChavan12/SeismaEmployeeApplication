package com.seisma.employee.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "tax")
@EnableConfigurationProperties
public class TaxRatesConfiguration {


  public List<Bracket> getRates() {
    return rates;
  }

  public void setRates(List<Bracket> rates) {
    this.rates = rates;
  }

  private List<Bracket> rates;


  public static class Bracket {

    private Integer min;

    private Integer max;

    private Float cents;

    private Integer offset;

    public Bracket() {
    }

    public Bracket(final Integer min, final Integer max, final Float cents, final Integer offset) {
      this.min = min;
      this.max = max;
      this.cents = cents;
      this.offset = offset;
    }

    public Integer getMin() {
      return min;
    }

    public void setMin(Integer min) {
      this.min = min;
    }

    public Integer getMax() {
      return max;
    }

    public void setMax(Integer max) {
      this.max = max;
    }

    public Float getCents() {
      return cents;
    }

    public void setCents(Float cents) {
      this.cents = cents;
    }

    public Integer getOffset() {
      return offset;
    }

    public void setOffset(Integer offset) {
      this.offset = offset;
    }

    @Override
    public String toString() {
      return "Bracket{"
              + "min=" + min
              + ", max=" + max
              + ", cents=" + cents
              + ", offset=" + offset
              + '}';
    }
  }
}
