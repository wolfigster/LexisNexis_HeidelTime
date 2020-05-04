package de.wolfig.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "@odata.context",
        "@odata.count",
        "value",
        "@odata.nextLink"
})
public class RObject {

    @JsonProperty("@odata.context")
    private String odataContext;
    @JsonProperty("@odata.count")
    private Integer odataCount;
    @JsonProperty("value")
    private List<Value> value = null;
    @JsonProperty("@odata.nextLink")
    private String odataNextLink;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public RObject() {
    }

    /**
     *
     * @param odataContext
     * @param odataCount
     * @param odataNextLink
     * @param value
     */
    public RObject(String odataContext, Integer odataCount, List<Value> value, String odataNextLink) {
        super();
        this.odataContext = odataContext;
        this.odataCount = odataCount;
        this.value = value;
        this.odataNextLink = odataNextLink;
    }

    @JsonProperty("@odata.context")
    public String getOdataContext() {
        return odataContext;
    }

    @JsonProperty("@odata.context")
    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public RObject withOdataContext(String odataContext) {
        this.odataContext = odataContext;
        return this;
    }

    @JsonProperty("@odata.count")
    public Integer getOdataCount() {
        return odataCount;
    }

    @JsonProperty("@odata.count")
    public void setOdataCount(Integer odataCount) {
        this.odataCount = odataCount;
    }

    public RObject withOdataCount(Integer odataCount) {
        this.odataCount = odataCount;
        return this;
    }

    @JsonProperty("value")
    public List<Value> getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(List<Value> value) {
        this.value = value;
    }

    public RObject withValue(List<Value> value) {
        this.value = value;
        return this;
    }

    @JsonProperty("@odata.nextLink")
    public String getOdataNextLink() {
        return odataNextLink;
    }

    @JsonProperty("@odata.nextLink")
    public void setOdataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
    }

    public RObject withOdataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public RObject withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
