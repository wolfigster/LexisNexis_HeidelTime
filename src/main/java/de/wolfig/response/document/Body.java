//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.05.09 um 09:45:06 PM CEST 
//


package de.wolfig.response.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="body.head"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="hedline"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="hl1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
 *         &lt;element name="body.content"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{}bodyText"/&gt;
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
    "bodyHead",
    "bodyContent"
})
@XmlRootElement(name = "body", namespace = "http://iptc.org/std/NITF/2006-10-18/")
public class Body {

    @XmlElement(name = "body.head", namespace = "http://iptc.org/std/NITF/2006-10-18/", required = true)
    protected BodyHead bodyHead;
    @XmlElement(name = "body.content", namespace = "http://iptc.org/std/NITF/2006-10-18/", required = true)
    protected BodyContent bodyContent;

    /**
     * Ruft den Wert der bodyHead-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BodyHead }
     *     
     */
    public BodyHead getBodyHead() {
        return bodyHead;
    }

    /**
     * Legt den Wert der bodyHead-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BodyHead }
     *     
     */
    public void setBodyHead(BodyHead value) {
        this.bodyHead = value;
    }

    /**
     * Ruft den Wert der bodyContent-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BodyContent }
     *     
     */
    public BodyContent getBodyContent() {
        return bodyContent;
    }

    /**
     * Legt den Wert der bodyContent-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BodyContent }
     *     
     */
    public void setBodyContent(BodyContent value) {
        this.bodyContent = value;
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
     *         &lt;element ref="{}bodyText"/&gt;
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
        "bodyText"
    })
    public static class BodyContent {

        @XmlElement(required = true)
        protected BodyText bodyText;

        /**
         * Ruft den Wert der bodyText-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BodyText }
         *     
         */
        public BodyText getBodyText() {
            return bodyText;
        }

        /**
         * Legt den Wert der bodyText-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BodyText }
         *     
         */
        public void setBodyText(BodyText value) {
            this.bodyText = value;
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
     *         &lt;element name="hedline"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="hl1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "hedline"
    })
    public static class BodyHead {

        @XmlElement(namespace = "http://iptc.org/std/NITF/2006-10-18/", required = true)
        protected Hedline hedline;

        /**
         * Ruft den Wert der hedline-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Hedline }
         *     
         */
        public Hedline getHedline() {
            return hedline;
        }

        /**
         * Legt den Wert der hedline-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Hedline }
         *     
         */
        public void setHedline(Hedline value) {
            this.hedline = value;
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
         *         &lt;element name="hl1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "hl1"
        })
        public static class Hedline {

            @XmlElement(namespace = "http://iptc.org/std/NITF/2006-10-18/", required = true)
            protected String hl1;

            /**
             * Ruft den Wert der hl1-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHl1() {
                return hl1;
            }

            /**
             * Legt den Wert der hl1-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHl1(String value) {
                this.hl1 = value;
            }

        }

    }

}
