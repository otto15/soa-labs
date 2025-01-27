
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPersonResponseTo complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="createPersonResponseTo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
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
@XmlType(name = "createPersonResponseTo", propOrder = {
    "passportID"
})
public class CreatePersonResponseTo {

    protected String passportID;

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
