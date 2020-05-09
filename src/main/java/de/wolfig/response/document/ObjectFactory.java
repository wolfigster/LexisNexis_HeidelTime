//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.05.09 um 09:45:06 PM CEST 
//


package de.wolfig.response.document;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.wolfig.response.document package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BodyTextPPerson_QNAME = new QName("", "person");
    private final static QName _ArticleDocMetadataClassificationGroupClassificationClassificationItemClassCode_QNAME = new QName("", "classCode");
    private final static QName _ArticleDocMetadataClassificationGroupClassificationClassificationItemClassName_QNAME = new QName("", "className");
    private final static QName _ArticleDocMetadataClassificationGroupClassificationClassificationItemClassificationItem_QNAME = new QName("", "classificationItem");
    private final static QName _ArticleDocMetadataPublicationInfoCopyrightBr_QNAME = new QName("", "br");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.wolfig.response.document
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Entry }
     * 
     */
    public Entry createEntry() {
        return new Entry();
    }

    /**
     * Create an instance of {@link ArticleDoc }
     * 
     */
    public ArticleDoc createArticleDoc() {
        return new ArticleDoc();
    }

    /**
     * Create an instance of {@link Body }
     * 
     */
    public Body createBody() {
        return new Body();
    }

    /**
     * Create an instance of {@link BodyText }
     * 
     */
    public BodyText createBodyText() {
        return new BodyText();
    }

    /**
     * Create an instance of {@link Metadata }
     * 
     */
    public Metadata createMetadata() {
        return new Metadata();
    }

    /**
     * Create an instance of {@link BodyText.P }
     * 
     */
    public BodyText.P createBodyTextP() {
        return new BodyText.P();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata }
     * 
     */
    public ArticleDoc.Metadata createArticleDocMetadata() {
        return new ArticleDoc.Metadata();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.ClassificationGroup }
     * 
     */
    public ArticleDoc.Metadata.ClassificationGroup createArticleDocMetadataClassificationGroup() {
        return new ArticleDoc.Metadata.ClassificationGroup();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.ClassificationGroup.Classification }
     * 
     */
    public ArticleDoc.Metadata.ClassificationGroup.Classification createArticleDocMetadataClassificationGroupClassification() {
        return new ArticleDoc.Metadata.ClassificationGroup.Classification();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem }
     * 
     */
    public ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem createArticleDocMetadataClassificationGroupClassificationClassificationItem() {
        return new ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.Classification }
     * 
     */
    public ArticleDoc.Metadata.Classification createArticleDocMetadataClassification() {
        return new ArticleDoc.Metadata.Classification();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.PublicationInfo }
     * 
     */
    public ArticleDoc.Metadata.PublicationInfo createArticleDocMetadataPublicationInfo() {
        return new ArticleDoc.Metadata.PublicationInfo();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.PublicationInfo.Classification }
     * 
     */
    public ArticleDoc.Metadata.PublicationInfo.Classification createArticleDocMetadataPublicationInfoClassification() {
        return new ArticleDoc.Metadata.PublicationInfo.Classification();
    }

    /**
     * Create an instance of {@link Body.BodyHead }
     * 
     */
    public Body.BodyHead createBodyBodyHead() {
        return new Body.BodyHead();
    }

    /**
     * Create an instance of {@link ArticleDoc.ArticleDocHead }
     * 
     */
    public ArticleDoc.ArticleDocHead createArticleDocArticleDocHead() {
        return new ArticleDoc.ArticleDocHead();
    }

    /**
     * Create an instance of {@link Entry.Author }
     * 
     */
    public Entry.Author createEntryAuthor() {
        return new Entry.Author();
    }

    /**
     * Create an instance of {@link Entry.Content }
     * 
     */
    public Entry.Content createEntryContent() {
        return new Entry.Content();
    }

    /**
     * Create an instance of {@link Body.BodyContent }
     * 
     */
    public Body.BodyContent createBodyBodyContent() {
        return new Body.BodyContent();
    }

    /**
     * Create an instance of {@link Metadata.Identifier }
     * 
     */
    public Metadata.Identifier createMetadataIdentifier() {
        return new Metadata.Identifier();
    }

    /**
     * Create an instance of {@link Metadata.Source }
     * 
     */
    public Metadata.Source createMetadataSource() {
        return new Metadata.Source();
    }

    /**
     * Create an instance of {@link Metadata.Date }
     * 
     */
    public Metadata.Date createMetadataDate() {
        return new Metadata.Date();
    }

    /**
     * Create an instance of {@link BodyText.P.Person }
     * 
     */
    public BodyText.P.Person createBodyTextPPerson() {
        return new BodyText.P.Person();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.WordCount }
     * 
     */
    public ArticleDoc.Metadata.WordCount createArticleDocMetadataWordCount() {
        return new ArticleDoc.Metadata.WordCount();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII }
     * 
     */
    public ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII createArticleDocMetadataClassificationGroupClassificationClassificationItemClassificationItem() {
        return new ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.Classification.ClassificationItem }
     * 
     */
    public ArticleDoc.Metadata.Classification.ClassificationItem createArticleDocMetadataClassificationClassificationItem() {
        return new ArticleDoc.Metadata.Classification.ClassificationItem();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.PublicationInfo.Copyright }
     * 
     */
    public ArticleDoc.Metadata.PublicationInfo.Copyright createArticleDocMetadataPublicationInfoCopyright() {
        return new ArticleDoc.Metadata.PublicationInfo.Copyright();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.PublicationInfo.PublicationDate }
     * 
     */
    public ArticleDoc.Metadata.PublicationInfo.PublicationDate createArticleDocMetadataPublicationInfoPublicationDate() {
        return new ArticleDoc.Metadata.PublicationInfo.PublicationDate();
    }

    /**
     * Create an instance of {@link ArticleDoc.Metadata.PublicationInfo.Classification.ClassificationItem }
     * 
     */
    public ArticleDoc.Metadata.PublicationInfo.Classification.ClassificationItem createArticleDocMetadataPublicationInfoClassificationClassificationItem() {
        return new ArticleDoc.Metadata.PublicationInfo.Classification.ClassificationItem();
    }

    /**
     * Create an instance of {@link Body.BodyHead.Hedline }
     * 
     */
    public Body.BodyHead.Hedline createBodyBodyHeadHedline() {
        return new Body.BodyHead.Hedline();
    }

    /**
     * Create an instance of {@link ArticleDoc.ArticleDocHead.ItemInfo }
     * 
     */
    public ArticleDoc.ArticleDocHead.ItemInfo createArticleDocArticleDocHeadItemInfo() {
        return new ArticleDoc.ArticleDocHead.ItemInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BodyText.P.Person }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BodyText.P.Person }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "person", scope = BodyText.P.class)
    public JAXBElement<BodyText.P.Person> createBodyTextPPerson(BodyText.P.Person value) {
        return new JAXBElement<BodyText.P.Person>(_BodyTextPPerson_QNAME, BodyText.P.Person.class, BodyText.P.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "classCode", scope = ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.class)
    public JAXBElement<String> createArticleDocMetadataClassificationGroupClassificationClassificationItemClassCode(String value) {
        return new JAXBElement<String>(_ArticleDocMetadataClassificationGroupClassificationClassificationItemClassCode_QNAME, String.class, ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "className", scope = ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.class)
    public JAXBElement<String> createArticleDocMetadataClassificationGroupClassificationClassificationItemClassName(String value) {
        return new JAXBElement<String>(_ArticleDocMetadataClassificationGroupClassificationClassificationItemClassName_QNAME, String.class, ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "classificationItem", scope = ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.class)
    public JAXBElement<ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII> createArticleDocMetadataClassificationGroupClassificationClassificationItemClassificationItem(ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII value) {
        return new JAXBElement<ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII>(_ArticleDocMetadataClassificationGroupClassificationClassificationItemClassificationItem_QNAME, ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.ClassificationItemII.class, ArticleDoc.Metadata.ClassificationGroup.Classification.ClassificationItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "br", scope = ArticleDoc.Metadata.PublicationInfo.Copyright.class)
    public JAXBElement<Object> createArticleDocMetadataPublicationInfoCopyrightBr(Object value) {
        return new JAXBElement<Object>(_ArticleDocMetadataPublicationInfoCopyrightBr_QNAME, Object.class, ArticleDoc.Metadata.PublicationInfo.Copyright.class, value);
    }

}
