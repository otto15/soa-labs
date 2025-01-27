
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPersonRequestTo complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="createPersonRequestTo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="eyeColor" type="{http://service.products.soa.com/}colorTo" minOccurs="0"/>
 *         <element name="hairColor" type="{http://service.products.soa.com/}colorTo" minOccurs="0"/>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="passportID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPersonRequestTo", propOrder = {
    "eyeColor",
    "hairColor",
    "name",
    "passportID"
})
public class CreatePersonRequestTo {

    @XmlSchemaType(name = "string")
    protected ColorTo eyeColor;
    @XmlSchemaType(name = "string")
    protected ColorTo hairColor;
    protected String name;
    protected String passportID;

    /**
     * Gets the value of the eyeColor property.
     * 
     * @return
     *     possible object is
     *     {@link ColorTo }
     *     
     */
    public ColorTo getEyeColor() {
        return eyeColor;
    }

    /**
     * Sets the value of the eyeColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorTo }
     *     
     */
    public void setEyeColor(ColorTo value) {
        this.eyeColor = value;
    }

    /**
     * Gets the value of the hairColor property.
     * 
     * @return
     *     possible object is
     *     {@link ColorTo }
     *     
     */
    public ColorTo getHairColor() {
        return hairColor;
    }

    /**
     * Sets the value of the hairColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorTo }
     *     
     */
    public void setHairColor(ColorTo value) {
        this.hairColor = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the passportID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Sets the value of the passportID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassportID(String value) {
        this.passportID = value;
    }

}
