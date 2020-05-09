package de.wolfig.response.list;

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
        "ResultId",
        "Jurisdiction",
        "Location",
        "ContentType",
        "Byline",
        "WordLength",
        "WebNewsUrl",
        "Geography",
        "NegativeNews",
        "Language",
        "Industry",
        "People",
        "Subject",
        "Section",
        "Company",
        "PublicationType",
        "Publisher",
        "GroupDuplicates",
        "InternationalLocation",
        "SearchType",
        "Date",
        "Keyword",
        "Title",
        "DocumentContent@odata.mediaReadLink",
        "DocumentContent@odata.mediaContentType",
        "Overview",
        "Extracts",
        "IsCitationMatch",
        "Source"
})
public class Value {

    @JsonProperty("ResultId")
    private String resultId;
    @JsonProperty("Jurisdiction")
    private String jurisdiction;
    @JsonProperty("Location")
    private Object location;
    @JsonProperty("ContentType")
    private String contentType;
    @JsonProperty("Byline")
    private Object byline;
    @JsonProperty("WordLength")
    private Integer wordLength;
    @JsonProperty("WebNewsUrl")
    private Object webNewsUrl;
    @JsonProperty("Geography")
    private Object geography;
    @JsonProperty("NegativeNews")
    private Object negativeNews;
    @JsonProperty("Language")
    private Object language;
    @JsonProperty("Industry")
    private Object industry;
    @JsonProperty("People")
    private Object people;
    @JsonProperty("Subject")
    private Object subject;
    @JsonProperty("Section")
    private Object section;
    @JsonProperty("Company")
    private Object company;
    @JsonProperty("PublicationType")
    private Object publicationType;
    @JsonProperty("Publisher")
    private Object publisher;
    @JsonProperty("GroupDuplicates")
    private Object groupDuplicates;
    @JsonProperty("InternationalLocation")
    private Object internationalLocation;
    @JsonProperty("SearchType")
    private String searchType;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Keyword")
    private Object keyword;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("DocumentContent@odata.mediaReadLink")
    private String documentContentOdataMediaReadLink;
    @JsonProperty("DocumentContent@odata.mediaContentType")
    private String documentContentOdataMediaContentType;
    @JsonProperty("Overview")
    private String overview;
    @JsonProperty("Extracts")
    private List<Extract> extracts = null;
    @JsonProperty("IsCitationMatch")
    private Boolean isCitationMatch;
    @JsonProperty("Source")
    private Source source;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Value() {
    }

    /**
     *
     * @param date
     * @param documentContentOdataMediaReadLink
     * @param subject
     * @param jurisdiction
     * @param internationalLocation
     * @param language
     * @param industry
     * @param section
     * @param source
     * @param title
     * @param documentContentOdataMediaContentType
     * @param geography
     * @param isCitationMatch
     * @param company
     * @param keyword
     * @param contentType
     * @param byline
     * @param overview
     * @param resultId
     * @param searchType
     * @param wordLength
     * @param publicationType
     * @param people
     * @param webNewsUrl
     * @param groupDuplicates
     * @param negativeNews
     * @param publisher
     * @param location
     * @param extracts
     */
    public Value(String resultId, String jurisdiction, Object location, String contentType, Object byline, Integer wordLength, Object webNewsUrl, Object geography, Object negativeNews, Object language, Object industry, Object people, Object subject, Object section, Object company, Object publicationType, Object publisher, Object groupDuplicates, Object internationalLocation, String searchType, String date, Object keyword, String title, String documentContentOdataMediaReadLink, String documentContentOdataMediaContentType, String overview, List<Extract> extracts, Boolean isCitationMatch, Source source) {
        super();
        this.resultId = resultId;
        this.jurisdiction = jurisdiction;
        this.location = location;
        this.contentType = contentType;
        this.byline = byline;
        this.wordLength = wordLength;
        this.webNewsUrl = webNewsUrl;
        this.geography = geography;
        this.negativeNews = negativeNews;
        this.language = language;
        this.industry = industry;
        this.people = people;
        this.subject = subject;
        this.section = section;
        this.company = company;
        this.publicationType = publicationType;
        this.publisher = publisher;
        this.groupDuplicates = groupDuplicates;
        this.internationalLocation = internationalLocation;
        this.searchType = searchType;
        this.date = date;
        this.keyword = keyword;
        this.title = title;
        this.documentContentOdataMediaReadLink = documentContentOdataMediaReadLink;
        this.documentContentOdataMediaContentType = documentContentOdataMediaContentType;
        this.overview = overview;
        this.extracts = extracts;
        this.isCitationMatch = isCitationMatch;
        this.source = source;
    }

    @JsonProperty("ResultId")
    public String getResultId() {
        return resultId;
    }

    @JsonProperty("ResultId")
    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public Value withResultId(String resultId) {
        this.resultId = resultId;
        return this;
    }

    @JsonProperty("Jurisdiction")
    public String getJurisdiction() {
        return jurisdiction;
    }

    @JsonProperty("Jurisdiction")
    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Value withJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
        return this;
    }

    @JsonProperty("Location")
    public Object getLocation() {
        return location;
    }

    @JsonProperty("Location")
    public void setLocation(Object location) {
        this.location = location;
    }

    public Value withLocation(Object location) {
        this.location = location;
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

    public Value withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @JsonProperty("Byline")
    public Object getByline() {
        return byline;
    }

    @JsonProperty("Byline")
    public void setByline(Object byline) {
        this.byline = byline;
    }

    public Value withByline(Object byline) {
        this.byline = byline;
        return this;
    }

    @JsonProperty("WordLength")
    public Integer getWordLength() {
        return wordLength;
    }

    @JsonProperty("WordLength")
    public void setWordLength(Integer wordLength) {
        this.wordLength = wordLength;
    }

    public Value withWordLength(Integer wordLength) {
        this.wordLength = wordLength;
        return this;
    }

    @JsonProperty("WebNewsUrl")
    public Object getWebNewsUrl() {
        return webNewsUrl;
    }

    @JsonProperty("WebNewsUrl")
    public void setWebNewsUrl(Object webNewsUrl) {
        this.webNewsUrl = webNewsUrl;
    }

    public Value withWebNewsUrl(Object webNewsUrl) {
        this.webNewsUrl = webNewsUrl;
        return this;
    }

    @JsonProperty("Geography")
    public Object getGeography() {
        return geography;
    }

    @JsonProperty("Geography")
    public void setGeography(Object geography) {
        this.geography = geography;
    }

    public Value withGeography(Object geography) {
        this.geography = geography;
        return this;
    }

    @JsonProperty("NegativeNews")
    public Object getNegativeNews() {
        return negativeNews;
    }

    @JsonProperty("NegativeNews")
    public void setNegativeNews(Object negativeNews) {
        this.negativeNews = negativeNews;
    }

    public Value withNegativeNews(Object negativeNews) {
        this.negativeNews = negativeNews;
        return this;
    }

    @JsonProperty("Language")
    public Object getLanguage() {
        return language;
    }

    @JsonProperty("Language")
    public void setLanguage(Object language) {
        this.language = language;
    }

    public Value withLanguage(Object language) {
        this.language = language;
        return this;
    }

    @JsonProperty("Industry")
    public Object getIndustry() {
        return industry;
    }

    @JsonProperty("Industry")
    public void setIndustry(Object industry) {
        this.industry = industry;
    }

    public Value withIndustry(Object industry) {
        this.industry = industry;
        return this;
    }

    @JsonProperty("People")
    public Object getPeople() {
        return people;
    }

    @JsonProperty("People")
    public void setPeople(Object people) {
        this.people = people;
    }

    public Value withPeople(Object people) {
        this.people = people;
        return this;
    }

    @JsonProperty("Subject")
    public Object getSubject() {
        return subject;
    }

    @JsonProperty("Subject")
    public void setSubject(Object subject) {
        this.subject = subject;
    }

    public Value withSubject(Object subject) {
        this.subject = subject;
        return this;
    }

    @JsonProperty("Section")
    public Object getSection() {
        return section;
    }

    @JsonProperty("Section")
    public void setSection(Object section) {
        this.section = section;
    }

    public Value withSection(Object section) {
        this.section = section;
        return this;
    }

    @JsonProperty("Company")
    public Object getCompany() {
        return company;
    }

    @JsonProperty("Company")
    public void setCompany(Object company) {
        this.company = company;
    }

    public Value withCompany(Object company) {
        this.company = company;
        return this;
    }

    @JsonProperty("PublicationType")
    public Object getPublicationType() {
        return publicationType;
    }

    @JsonProperty("PublicationType")
    public void setPublicationType(Object publicationType) {
        this.publicationType = publicationType;
    }

    public Value withPublicationType(Object publicationType) {
        this.publicationType = publicationType;
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

    public Value withPublisher(Object publisher) {
        this.publisher = publisher;
        return this;
    }

    @JsonProperty("GroupDuplicates")
    public Object getGroupDuplicates() {
        return groupDuplicates;
    }

    @JsonProperty("GroupDuplicates")
    public void setGroupDuplicates(Object groupDuplicates) {
        this.groupDuplicates = groupDuplicates;
    }

    public Value withGroupDuplicates(Object groupDuplicates) {
        this.groupDuplicates = groupDuplicates;
        return this;
    }

    @JsonProperty("InternationalLocation")
    public Object getInternationalLocation() {
        return internationalLocation;
    }

    @JsonProperty("InternationalLocation")
    public void setInternationalLocation(Object internationalLocation) {
        this.internationalLocation = internationalLocation;
    }

    public Value withInternationalLocation(Object internationalLocation) {
        this.internationalLocation = internationalLocation;
        return this;
    }

    @JsonProperty("SearchType")
    public String getSearchType() {
        return searchType;
    }

    @JsonProperty("SearchType")
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public Value withSearchType(String searchType) {
        this.searchType = searchType;
        return this;
    }

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(String date) {
        this.date = date;
    }

    public Value withDate(String date) {
        this.date = date;
        return this;
    }

    @JsonProperty("Keyword")
    public Object getKeyword() {
        return keyword;
    }

    @JsonProperty("Keyword")
    public void setKeyword(Object keyword) {
        this.keyword = keyword;
    }

    public Value withKeyword(Object keyword) {
        this.keyword = keyword;
        return this;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Value withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("DocumentContent@odata.mediaReadLink")
    public String getDocumentContentOdataMediaReadLink() {
        return documentContentOdataMediaReadLink;
    }

    @JsonProperty("DocumentContent@odata.mediaReadLink")
    public void setDocumentContentOdataMediaReadLink(String documentContentOdataMediaReadLink) {
        this.documentContentOdataMediaReadLink = documentContentOdataMediaReadLink;
    }

    public Value withDocumentContentOdataMediaReadLink(String documentContentOdataMediaReadLink) {
        this.documentContentOdataMediaReadLink = documentContentOdataMediaReadLink;
        return this;
    }

    @JsonProperty("DocumentContent@odata.mediaContentType")
    public String getDocumentContentOdataMediaContentType() {
        return documentContentOdataMediaContentType;
    }

    @JsonProperty("DocumentContent@odata.mediaContentType")
    public void setDocumentContentOdataMediaContentType(String documentContentOdataMediaContentType) {
        this.documentContentOdataMediaContentType = documentContentOdataMediaContentType;
    }

    public Value withDocumentContentOdataMediaContentType(String documentContentOdataMediaContentType) {
        this.documentContentOdataMediaContentType = documentContentOdataMediaContentType;
        return this;
    }

    @JsonProperty("Overview")
    public String getOverview() {
        return overview;
    }

    @JsonProperty("Overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Value withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    @JsonProperty("Extracts")
    public List<Extract> getExtracts() {
        return extracts;
    }

    @JsonProperty("Extracts")
    public void setExtracts(List<Extract> extracts) {
        this.extracts = extracts;
    }

    public Value withExtracts(List<Extract> extracts) {
        this.extracts = extracts;
        return this;
    }

    @JsonProperty("IsCitationMatch")
    public Boolean getIsCitationMatch() {
        return isCitationMatch;
    }

    @JsonProperty("IsCitationMatch")
    public void setIsCitationMatch(Boolean isCitationMatch) {
        this.isCitationMatch = isCitationMatch;
    }

    public Value withIsCitationMatch(Boolean isCitationMatch) {
        this.isCitationMatch = isCitationMatch;
        return this;
    }

    @JsonProperty("Source")
    public Source getSource() {
        return source;
    }

    @JsonProperty("Source")
    public void setSource(Source source) {
        this.source = source;
    }

    public Value withSource(Source source) {
        this.source = source;
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

    public Value withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}