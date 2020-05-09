package de.wolfig.response.list;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Type",
        "SummaryText"
})
public class Extract {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("SummaryText")
    private String summaryText;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Extract() {
    }

    /**
     *
     * @param summaryText
     * @param type
     */
    public Extract(String type, String summaryText) {
        super();
        this.type = type;
        this.summaryText = summaryText;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    public Extract withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("SummaryText")
    public String getSummaryText() {
        return summaryText;
    }

    @JsonProperty("SummaryText")
    public void setSummaryText(String summaryText) {
        this.summaryText = summaryText;
    }

    public Extract withSummaryText(String summaryText) {
        this.summaryText = summaryText;
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

    public Extract withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}