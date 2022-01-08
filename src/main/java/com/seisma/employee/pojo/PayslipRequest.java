
package com.seisma.employee.pojo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "firstName",
    "lastName",
    "annualSalary",
    "paymentMonth",
    "superRate"
})
public class PayslipRequest {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("annualSalary")
    private Integer annualSalary;
    @JsonProperty("paymentMonth")
    private Integer paymentMonth;
    @JsonProperty("superRate")
    private Double superRate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("annualSalary")
    public Integer getAnnualSalary() {
        return annualSalary;
    }

    @JsonProperty("annualSalary")
    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }

    @JsonProperty("paymentMonth")
    public Integer getPaymentMonth() {
        return paymentMonth;
    }

    @JsonProperty("paymentMonth")
    public void setPaymentMonth(Integer paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    @JsonProperty("superRate")
    public Double getSuperRate() {
        return superRate;
    }

    @JsonProperty("superRate")
    public void setSuperRate(Double superRate) {
        this.superRate = superRate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
