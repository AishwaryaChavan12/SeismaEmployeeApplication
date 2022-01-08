
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
    "employee",
    "fromDate",
    "toDate",
    "grossIncome",
    "incomeTax",
    "superannuation",
    "netIncome"
})
@Generated("jsonschema2pojo")
public class PayslipResponse {

    @JsonProperty("employee")
    private PayslipRequest employee;
    @JsonProperty("fromDate")
    private String fromDate;
    @JsonProperty("toDate")
    private String toDate;
    @JsonProperty("grossIncome")
    private Integer grossIncome;
    @JsonProperty("incomeTax")
    private Integer incomeTax;
    @JsonProperty("superannuation")
    private Long superannuation;
    @JsonProperty("netIncome")
    private Integer netIncome;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("employee")
    public PayslipRequest getEmployee() {
        return employee;
    }

    @JsonProperty("employee")
    public void setEmployee(PayslipRequest employee) {
        this.employee = employee;
    }

    @JsonProperty("fromDate")
    public String getFromDate() {
        return fromDate;
    }

    @JsonProperty("fromDate")
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    @JsonProperty("toDate")
    public String getToDate() {
        return toDate;
    }

    @JsonProperty("toDate")
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @JsonProperty("grossIncome")
    public Integer getGrossIncome() {
        return grossIncome;
    }

    @JsonProperty("grossIncome")
    public void setGrossIncome(Integer grossIncome) {
        this.grossIncome = grossIncome;
    }

    @JsonProperty("incomeTax")
    public Integer getIncomeTax() {
        return incomeTax;
    }

    @JsonProperty("incomeTax")
    public void setIncomeTax(Integer incomeTax) {
        this.incomeTax = incomeTax;
    }

    @JsonProperty("superannuation")
    public Long getSuperannuation() {
        return superannuation;
    }

    @JsonProperty("superannuation")
    public void setSuperannuation(Long superannuation) {
        this.superannuation = superannuation;
    }

    @JsonProperty("netIncome")
    public Integer getNetIncome() {
        return netIncome;
    }

    @JsonProperty("netIncome")
    public void setNetIncome(Integer netIncome) {
        this.netIncome = netIncome;
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
