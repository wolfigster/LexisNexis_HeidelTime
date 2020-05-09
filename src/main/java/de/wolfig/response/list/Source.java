package de.wolfig.response.list;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "Name",
        "ContentType",
        "Jurisdiction",
        "Publisher",
        "AlphaCategory"
})
public class Source {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("ContentType")
    private String contentType;
    @JsonProperty("Jurisdiction")
    private Object jurisdiction;
    @JsonProperty("Publisher")
    private Object publisher;
    @JsonProperty("AlphaCategory")
    private Object alphaCategory;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Source() {
    }

    /**
     *
     * @param jurisdiction
     * @param name
     * @param publisher
     * @param alphaCategory
     * @param id
     * @param contentType
     */
    public Source(String id, String name, String contentType, Object jurisdiction, Object publisher, Object alphaCategory) {
        super();
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.jurisdiction = jurisdiction;
        this.publisher = publisher;
        this.alphaCategory = alphaCategory;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    public Source withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public Source withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("ContentType")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("ContentType")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Source withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @JsonProperty("Jurisdiction")
    public Object getJurisdiction() {
        return jurisdiction;
    }

    @JsonProperty("Jurisdiction")
    public void setJurisdiction(Object jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Source withJurisdiction(Object jurisdiction) {
        this.jurisdiction = jurisdiction;
        return this;
    }

    @JsonProperty("Publisher")
    public Object getPublisher() {
        return publisher;
    }

    @JsonProperty("Publisher")
    public void setPublisher(Object publisher) {
        this.publisher = publisher;
    }

    public Source withPublisher(Object publisher) {
        this.publisher = publisher;
        return this;
    }

    @JsonProperty("AlphaCategory")
    public Object getAlphaCategory() {
        return alphaCategory;
    }

    @JsonProperty("AlphaCategory")
    public void setAlphaCategory(Object alphaCategory) {
        this.alphaCategory = alphaCategory;
    }

    public Source withAlphaCategory(Object alphaCategory) {
        this.alphaCategory = alphaCategory;
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

    public Source withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}