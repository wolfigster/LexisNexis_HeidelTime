//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.05.09 um 09:45:06 PM CEST 
//


package de.wolfig.response.document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse f�r anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="articleDocHead"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="itemInfo"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="transcriptNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://iptc.org/std/NITF/2006-10-18/}body"/&gt;
 *         &lt;element name="metadata"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{http://purl.org/dc/elements/1.1/}metadata"/&gt;
 *                   &lt;element name="wordCount"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="number" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="publicationInfo"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="copyright" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence minOccurs="0"&gt;
 *                                       &lt;element name="br" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="publicationName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="publicationDate"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="dateText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="day" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
 *                                     &lt;attribute name="month" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
 *                                     &lt;attribute name="year" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="classification" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="classificationItem"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;sequence&gt;
 *                                                 &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                               &lt;/sequence&gt;
 *                                             &lt;/restriction&gt;
 *                                           &lt;/complexContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="classification"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="classificationItem"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="classificationGroup" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="classification" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="classificationItem" maxOccurs="unbounded"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;complexContent&gt;
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                               &lt;sequence&gt;
 *                                                 &lt;choice maxOccurs="unbounded"&gt;
 *                                                   &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                                   &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                                   &lt;element name="classificationItem"&gt;
 *                                                     &lt;complexType&gt;
 *                                                       &lt;complexContent&gt;
 *                                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                                           &lt;sequence&gt;
 *                                                             &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                                             &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                                           &lt;/sequence&gt;
 *                                                           &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
 *                                                         &lt;/restriction&gt;
 *                                                       &lt;/complexContent&gt;
 *                                                     &lt;/complexType&gt;
 *                                                   &lt;/element&gt;
 *                                                 &lt;/choice&gt;
 *                                               &lt;/sequence&gt;
 *                                               &lt;attribute name="score" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
 *                                             &lt;/restriction&gt;
 *                                           &lt;/complexContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                     &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="schemaVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang use="required""/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "articleDocHead",
    "body",
    "metadata"
})
@XmlRootElement(name = "articleDoc")
public class ArticleDoc {

    @XmlElement(required = true)
    protected ArticleDocHead articleDocHead;
    @XmlElement(namespace = "http://iptc.org/std/NITF/2006-10-18/", required = true)
    protected Body body;
    @XmlElement(required = true)
    protected Metadata metadata;
    @XmlAttribute(name = "schemaVersion", required = true)
    protected BigDecimal schemaVersion;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Ruft den Wert der articleDocHead-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ArticleDocHead }
     *     
     */
    public ArticleDocHead getArticleDocHead() {
        return articleDocHead;
    }

    /**
     * Legt den Wert der articleDocHead-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticleDocHead }
     *     
     */
    public void setArticleDocHead(ArticleDocHead value) {
        this.articleDocHead = value;
    }

    /**
     * Ruft den Wert der body-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Body }
     *     
     */
    public Body getBody() {
        return body;
    }

    /**
     * Legt den Wert der body-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Body }
     *     
     */
    public void setBody(Body value) {
        this.body = value;
    }

    /**
     * Ruft den Wert der metadata-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Metadata }
     *     
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * Legt den Wert der metadata-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Metadata }
     *     
     */
    public void setMetadata(Metadata value) {
        this.metadata = value;
    }

    /**
     * Ruft den Wert der schemaVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSchemaVersion() {
        return schemaVersion;
    }

    /**
     * Legt den Wert der schemaVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSchemaVersion(BigDecimal value) {
        this.schemaVersion = value;
    }

    /**
     * Ruft den Wert der lang-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Legt den Wert der lang-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="itemInfo"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="transcriptNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "itemInfo"
    })
    public static class ArticleDocHead {

        @XmlElement(required = true)
        protected ItemInfo itemInfo;

        /**
         * Ruft den Wert der itemInfo-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link ItemInfo }
         *     
         */
        public ItemInfo getItemInfo() {
            return itemInfo;
        }

        /**
         * Legt den Wert der itemInfo-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link ItemInfo }
         *     
         */
        public void setItemInfo(ItemInfo value) {
            this.itemInfo = value;
        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="transcriptNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "transcriptNumber"
        })
        public static class ItemInfo {

            @XmlElement(required = true)
            protected String transcriptNumber;

            /**
             * Ruft den Wert der transcriptNumber-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTranscriptNumber() {
                return transcriptNumber;
            }

            /**
             * Legt den Wert der transcriptNumber-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTranscriptNumber(String value) {
                this.transcriptNumber = value;
            }

        }

    }


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element ref="{http://purl.org/dc/elements/1.1/}metadata"/&gt;
     *         &lt;element name="wordCount"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="number" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="publicationInfo"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="copyright" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence minOccurs="0"&gt;
     *                             &lt;element name="br" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="publicationName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="publicationDate"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="dateText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="day" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
     *                           &lt;attribute name="month" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
     *                           &lt;attribute name="year" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="classification" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="classificationItem"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;sequence&gt;
     *                                       &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                     &lt;/sequence&gt;
     *                                   &lt;/restriction&gt;
     *                                 &lt;/complexContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="classification"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="classificationItem"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="classificationGroup" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="classification" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="classificationItem" maxOccurs="unbounded"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;complexContent&gt;
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                     &lt;sequence&gt;
     *                                       &lt;choice maxOccurs="unbounded"&gt;
     *                                         &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                         &lt;element name="classificationItem"&gt;
     *                                           &lt;complexType&gt;
     *                                             &lt;complexContent&gt;
     *                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                                                 &lt;sequence&gt;
     *                                                   &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                                   &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                                                 &lt;/sequence&gt;
     *                                                 &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
     *                                               &lt;/restriction&gt;
     *                                             &lt;/complexContent&gt;
     *                                           &lt;/complexType&gt;
     *                                         &lt;/element&gt;
     *                                       &lt;/choice&gt;
     *                                     &lt;/sequence&gt;
     *                                     &lt;attribute name="score" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
     *                                   &lt;/restriction&gt;
     *                                 &lt;/complexContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
     *                           &lt;/sequence&gt;
     *                           &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "metadata",
        "wordCount",
        "publicationInfo",
        "classification",
        "classificationGroup"
    })
    public static class Metadata {

        @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", required = true)
        protected de.wolfig.response.document.Metadata metadata;
        @XmlElement(required = true)
        protected WordCount wordCount;
        @XmlElement(required = true)
        protected PublicationInfo publicationInfo;
        @XmlElement(required = true)
        protected Classification classification;
        @XmlElement(required = true)
        protected List<ClassificationGroup> classificationGroup;

        /**
         * Ruft den Wert der metadata-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link de.wolfig.response.document.Metadata }
         *     
         */
        public de.wolfig.response.document.Metadata getMetadata() {
            return metadata;
        }

        /**
         * Legt den Wert der metadata-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link de.wolfig.response.document.Metadata }
         *     
         */
        public void setMetadata(de.wolfig.response.document.Metadata value) {
            this.metadata = value;
        }

        /**
         * Ruft den Wert der wordCount-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link WordCount }
         *     
         */
        public WordCount getWordCount() {
            return wordCount;
        }

        /**
         * Legt den Wert der wordCount-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link WordCount }
         *     
         */
        public void setWordCount(WordCount value) {
            this.wordCount = value;
        }

        /**
         * Ruft den Wert der publicationInfo-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link PublicationInfo }
         *     
         */
        public PublicationInfo getPublicationInfo() {
            return publicationInfo;
        }

        /**
         * Legt den Wert der publicationInfo-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link PublicationInfo }
         *     
         */
        public void setPublicationInfo(PublicationInfo value) {
            this.publicationInfo = value;
        }

        /**
         * Ruft den Wert der classification-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Classification }
         *     
         */
        public Classification getClassification() {
            return classification;
        }

        /**
         * Legt den Wert der classification-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Classification }
         *     
         */
        public void setClassification(Classification value) {
            this.classification = value;
        }

        /**
         * Gets the value of the classificationGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the classificationGroup property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClassificationGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ClassificationGroup }
         * 
         * 
         */
        public List<ClassificationGroup> getClassificationGroup() {
            if (classificationGroup == null) {
                classificationGroup = new ArrayList<ClassificationGroup>();
            }
            return this.classificationGroup;
        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="classificationItem"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "classificationItem"
        })
        public static class Classification {

            @XmlElement(required = true)
            protected ClassificationItem classificationItem;
            @XmlAttribute(name = "classificationScheme", required = true)
            protected String classificationScheme;

            /**
             * Ruft den Wert der classificationItem-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link ClassificationItem }
             *     
             */
            public ClassificationItem getClassificationItem() {
                return classificationItem;
            }

            /**
             * Legt den Wert der classificationItem-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link ClassificationItem }
             *     
             */
            public void setClassificationItem(ClassificationItem value) {
                this.classificationItem = value;
            }

            /**
             * Ruft den Wert der classificationScheme-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getClassificationScheme() {
                return classificationScheme;
            }

            /**
             * Legt den Wert der classificationScheme-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setClassificationScheme(String value) {
                this.classificationScheme = value;
            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "className"
            })
            public static class ClassificationItem {

                @XmlElement(required = true)
                protected String className;
                @XmlAttribute(name = "classificationScheme", required = true)
                protected String classificationScheme;

                /**
                 * Ruft den Wert der className-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getClassName() {
                    return className;
                }

                /**
                 * Legt den Wert der className-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setClassName(String value) {
                    this.className = value;
                }

                /**
                 * Ruft den Wert der classificationScheme-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getClassificationScheme() {
                    return classificationScheme;
                }

                /**
                 * Legt den Wert der classificationScheme-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setClassificationScheme(String value) {
                    this.classificationScheme = value;
                }

            }

        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="classification" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="classificationItem" maxOccurs="unbounded"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;sequence&gt;
         *                             &lt;choice maxOccurs="unbounded"&gt;
         *                               &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                               &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                               &lt;element name="classificationItem"&gt;
         *                                 &lt;complexType&gt;
         *                                   &lt;complexContent&gt;
         *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                                       &lt;sequence&gt;
         *                                         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                                         &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                                       &lt;/sequence&gt;
         *                                       &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
         *                                     &lt;/restriction&gt;
         *                                   &lt;/complexContent&gt;
         *                                 &lt;/complexType&gt;
         *                               &lt;/element&gt;
         *                             &lt;/choice&gt;
         *                           &lt;/sequence&gt;
         *                           &lt;attribute name="score" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
         *                         &lt;/restriction&gt;
         *                       &lt;/complexContent&gt;
         *                     &lt;/complexType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "classification"
        })
        public static class ClassificationGroup {

            @XmlElement(required = true)
            protected List<Classification> classification;
            @XmlAttribute(name = "classificationScheme", required = true)
            protected String classificationScheme;

            /**
             * Gets the value of the classification property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the classification property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getClassification().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Classification }
             * 
             * 
             */
            public List<Classification> getClassification() {
                if (classification == null) {
                    classification = new ArrayList<Classification>();
                }
                return this.classification;
            }

            /**
             * Ruft den Wert der classificationScheme-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getClassificationScheme() {
                return classificationScheme;
            }

            /**
             * Legt den Wert der classificationScheme-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setClassificationScheme(String value) {
                this.classificationScheme = value;
            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="classificationItem" maxOccurs="unbounded"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;sequence&gt;
             *                   &lt;choice maxOccurs="unbounded"&gt;
             *                     &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                     &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                     &lt;element name="classificationItem"&gt;
             *                       &lt;complexType&gt;
             *                         &lt;complexContent&gt;
             *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                             &lt;sequence&gt;
             *                               &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                               &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                             &lt;/sequence&gt;
             *                             &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
             *                           &lt;/restriction&gt;
             *                         &lt;/complexContent&gt;
             *                       &lt;/complexType&gt;
             *                     &lt;/element&gt;
             *                   &lt;/choice&gt;
             *                 &lt;/sequence&gt;
             *                 &lt;attribute name="score" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
             *               &lt;/restriction&gt;
             *             &lt;/complexContent&gt;
             *           &lt;/complexType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "classificationItem"
            })
            public static class Classification {

                @XmlElement(required = true)
                protected List<ClassificationItem> classificationItem;
                @XmlAttribute(name = "classificationScheme", required = true)
                protected String classificationScheme;

                /**
                 * Gets the value of the classificationItem property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the classificationItem property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getClassificationItem().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ClassificationItem }
                 * 
                 * 
                 */
                public List<ClassificationItem> getClassificationItem() {
                    if (classificationItem == null) {
                        classificationItem = new ArrayList<ClassificationItem>();
                    }
                    return this.classificationItem;
                }

                /**
                 * Ruft den Wert der classificationScheme-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getClassificationScheme() {
                    return classificationScheme;
                }

                /**
                 * Legt den Wert der classificationScheme-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setClassificationScheme(String value) {
                    this.classificationScheme = value;
                }


                /**
                 * <p>Java-Klasse f�r anonymous complex type.
                 * 
                 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
                 * 
                 * <pre>
                 * &lt;complexType&gt;
                 *   &lt;complexContent&gt;
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *       &lt;sequence&gt;
                 *         &lt;choice maxOccurs="unbounded"&gt;
                 *           &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *           &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *           &lt;element name="classificationItem"&gt;
                 *             &lt;complexType&gt;
                 *               &lt;complexContent&gt;
                 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *                   &lt;sequence&gt;
                 *                     &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *                     &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *                   &lt;/sequence&gt;
                 *                   &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
                 *                 &lt;/restriction&gt;
                 *               &lt;/complexContent&gt;
                 *             &lt;/complexType&gt;
                 *           &lt;/element&gt;
                 *         &lt;/choice&gt;
                 *       &lt;/sequence&gt;
                 *       &lt;attribute name="score" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
                 *     &lt;/restriction&gt;
                 *   &lt;/complexContent&gt;
                 * &lt;/complexType&gt;
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "classCodeOrClassNameOrClassificationItem"
                })
                public static class ClassificationItem {

                    @XmlElementRefs({
                        @XmlElementRef(name = "classCode", type = JAXBElement.class, required = false),
                        @XmlElementRef(name = "className", type = JAXBElement.class, required = false),
                        @XmlElementRef(name = "classificationItem", type = JAXBElement.class, required = false)
                    })
                    protected List<JAXBElement<?>> classCodeOrClassNameOrClassificationItem;
                    @XmlAttribute(name = "score")
                    @XmlSchemaType(name = "unsignedByte")
                    protected Short score;

                    /**
                     * Gets the value of the classCodeOrClassNameOrClassificationItem property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the classCodeOrClassNameOrClassificationItem property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getClassCodeOrClassNameOrClassificationItem().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link JAXBElement }{@code <}{@link String }{@code >}
                     * {@link JAXBElement }{@code <}{@link String }{@code >}
                     * {@link JAXBElement }{@code <}{@link ClassificationItem }{@code >}
                     * 
                     * 
                     */
                    public List<JAXBElement<?>> getClassCodeOrClassNameOrClassificationItem() {
                        if (classCodeOrClassNameOrClassificationItem == null) {
                            classCodeOrClassNameOrClassificationItem = new ArrayList<JAXBElement<?>>();
                        }
                        return this.classCodeOrClassNameOrClassificationItem;
                    }

                    /**
                     * Ruft den Wert der score-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Short }
                     *     
                     */
                    public Short getScore() {
                        return score;
                    }

                    /**
                     * Legt den Wert der score-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Short }
                     *     
                     */
                    public void setScore(Short value) {
                        this.score = value;
                    }


                    /**
                     * <p>Java-Klasse f�r anonymous complex type.
                     * 
                     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
                     * 
                     * <pre>
                     * &lt;complexType&gt;
                     *   &lt;complexContent&gt;
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                     *       &lt;sequence&gt;
                     *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                     *         &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                     *       &lt;/sequence&gt;
                     *       &lt;attribute name="score" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
                     *     &lt;/restriction&gt;
                     *   &lt;/complexContent&gt;
                     * &lt;/complexType&gt;
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "className",
                        "classCode"
                    })
                    public static class ClassificationItemII {

                        @XmlElement(required = true)
                        protected String className;
                        @XmlElement(required = true)
                        protected String classCode;
                        @XmlAttribute(name = "score", required = true)
                        @XmlSchemaType(name = "unsignedByte")
                        protected short score;

                        /**
                         * Ruft den Wert der className-Eigenschaft ab.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getClassName() {
                            return className;
                        }

                        /**
                         * Legt den Wert der className-Eigenschaft fest.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setClassName(String value) {
                            this.className = value;
                        }

                        /**
                         * Ruft den Wert der classCode-Eigenschaft ab.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getClassCode() {
                            return classCode;
                        }

                        /**
                         * Legt den Wert der classCode-Eigenschaft fest.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setClassCode(String value) {
                            this.classCode = value;
                        }

                        /**
                         * Ruft den Wert der score-Eigenschaft ab.
                         * 
                         */
                        public short getScore() {
                            return score;
                        }

                        /**
                         * Legt den Wert der score-Eigenschaft fest.
                         * 
                         */
                        public void setScore(short value) {
                            this.score = value;
                        }

                    }

                }

            }

        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="copyright" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence minOccurs="0"&gt;
         *                   &lt;element name="br" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="publicationName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="publicationDate"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="dateText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="day" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
         *                 &lt;attribute name="month" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
         *                 &lt;attribute name="year" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="classification" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="classificationItem"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;complexContent&gt;
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                           &lt;sequence&gt;
         *                             &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                           &lt;/sequence&gt;
         *                         &lt;/restriction&gt;
         *                       &lt;/complexContent&gt;
         *                     &lt;/complexType&gt;
         *                   &lt;/element&gt;
         *                 &lt;/sequence&gt;
         *                 &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "copyright",
            "publicationName",
            "publicationDate",
            "classification"
        })
        public static class PublicationInfo {

            @XmlElement(required = true)
            protected List<Copyright> copyright;
            @XmlElement(required = true)
            protected String publicationName;
            @XmlElement(required = true)
            protected PublicationDate publicationDate;
            @XmlElement(required = true)
            protected List<Classification> classification;

            /**
             * Gets the value of the copyright property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the copyright property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCopyright().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Copyright }
             * 
             * 
             */
            public List<Copyright> getCopyright() {
                if (copyright == null) {
                    copyright = new ArrayList<Copyright>();
                }
                return this.copyright;
            }

            /**
             * Ruft den Wert der publicationName-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPublicationName() {
                return publicationName;
            }

            /**
             * Legt den Wert der publicationName-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPublicationName(String value) {
                this.publicationName = value;
            }

            /**
             * Ruft den Wert der publicationDate-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link PublicationDate }
             *     
             */
            public PublicationDate getPublicationDate() {
                return publicationDate;
            }

            /**
             * Legt den Wert der publicationDate-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link PublicationDate }
             *     
             */
            public void setPublicationDate(PublicationDate value) {
                this.publicationDate = value;
            }

            /**
             * Gets the value of the classification property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the classification property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getClassification().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Classification }
             * 
             * 
             */
            public List<Classification> getClassification() {
                if (classification == null) {
                    classification = new ArrayList<Classification>();
                }
                return this.classification;
            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="classificationItem"&gt;
             *           &lt;complexType&gt;
             *             &lt;complexContent&gt;
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *                 &lt;sequence&gt;
             *                   &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *                 &lt;/sequence&gt;
             *               &lt;/restriction&gt;
             *             &lt;/complexContent&gt;
             *           &lt;/complexType&gt;
             *         &lt;/element&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="classificationScheme" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "classificationItem"
            })
            public static class Classification {

                @XmlElement(required = true)
                protected ClassificationItem classificationItem;
                @XmlAttribute(name = "classificationScheme", required = true)
                protected String classificationScheme;

                /**
                 * Ruft den Wert der classificationItem-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link ClassificationItem }
                 *     
                 */
                public ClassificationItem getClassificationItem() {
                    return classificationItem;
                }

                /**
                 * Legt den Wert der classificationItem-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link ClassificationItem }
                 *     
                 */
                public void setClassificationItem(ClassificationItem value) {
                    this.classificationItem = value;
                }

                /**
                 * Ruft den Wert der classificationScheme-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getClassificationScheme() {
                    return classificationScheme;
                }

                /**
                 * Legt den Wert der classificationScheme-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setClassificationScheme(String value) {
                    this.classificationScheme = value;
                }


                /**
                 * <p>Java-Klasse f�r anonymous complex type.
                 * 
                 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
                 * 
                 * <pre>
                 * &lt;complexType&gt;
                 *   &lt;complexContent&gt;
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
                 *       &lt;sequence&gt;
                 *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
                 *       &lt;/sequence&gt;
                 *     &lt;/restriction&gt;
                 *   &lt;/complexContent&gt;
                 * &lt;/complexType&gt;
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "className"
                })
                public static class ClassificationItem {

                    @XmlElement(required = true)
                    protected String className;

                    /**
                     * Ruft den Wert der className-Eigenschaft ab.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getClassName() {
                        return className;
                    }

                    /**
                     * Legt den Wert der className-Eigenschaft fest.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setClassName(String value) {
                        this.className = value;
                    }

                }

            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence minOccurs="0"&gt;
             *         &lt;element name="br" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "content"
            })
            public static class Copyright {

                @XmlElementRef(name = "br", type = JAXBElement.class, required = false)
                @XmlMixed
                protected List<Serializable> content;

                /**
                 * Gets the value of the content property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the content property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getContent().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link JAXBElement }{@code <}{@link Object }{@code >}
                 * {@link String }
                 * 
                 * 
                 */
                public List<Serializable> getContent() {
                    if (content == null) {
                        content = new ArrayList<Serializable>();
                    }
                    return this.content;
                }

            }


            /**
             * <p>Java-Klasse f�r anonymous complex type.
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="dateText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *       &lt;/sequence&gt;
             *       &lt;attribute name="day" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
             *       &lt;attribute name="month" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" /&gt;
             *       &lt;attribute name="year" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "dateText"
            })
            public static class PublicationDate {

                @XmlElement(required = true)
                protected String dateText;
                @XmlAttribute(name = "day", required = true)
                @XmlSchemaType(name = "unsignedByte")
                protected short day;
                @XmlAttribute(name = "month", required = true)
                @XmlSchemaType(name = "unsignedByte")
                protected short month;
                @XmlAttribute(name = "year", required = true)
                @XmlSchemaType(name = "unsignedShort")
                protected int year;

                /**
                 * Ruft den Wert der dateText-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDateText() {
                    return dateText;
                }

                /**
                 * Legt den Wert der dateText-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDateText(String value) {
                    this.dateText = value;
                }

                /**
                 * Ruft den Wert der day-Eigenschaft ab.
                 * 
                 */
                public short getDay() {
                    return day;
                }

                /**
                 * Legt den Wert der day-Eigenschaft fest.
                 * 
                 */
                public void setDay(short value) {
                    this.day = value;
                }

                /**
                 * Ruft den Wert der month-Eigenschaft ab.
                 * 
                 */
                public short getMonth() {
                    return month;
                }

                /**
                 * Legt den Wert der month-Eigenschaft fest.
                 * 
                 */
                public void setMonth(short value) {
                    this.month = value;
                }

                /**
                 * Ruft den Wert der year-Eigenschaft ab.
                 * 
                 */
                public int getYear() {
                    return year;
                }

                /**
                 * Legt den Wert der year-Eigenschaft fest.
                 * 
                 */
                public void setYear(int value) {
                    this.year = value;
                }

            }

        }


        /**
         * <p>Java-Klasse f�r anonymous complex type.
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;attribute name="number" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class WordCount {

            @XmlAttribute(name = "number", required = true)
            @XmlSchemaType(name = "unsignedShort")
            protected int number;

            /**
             * Ruft den Wert der number-Eigenschaft ab.
             * 
             */
            public int getNumber() {
                return number;
            }

            /**
             * Legt den Wert der number-Eigenschaft fest.
             * 
             */
            public void setNumber(int value) {
                this.number = value;
            }

        }

    }

}
