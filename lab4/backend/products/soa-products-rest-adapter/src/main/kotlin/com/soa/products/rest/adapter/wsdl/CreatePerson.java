
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPerson complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="createPerson">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="createPersonRequestTo" type="{http://service.products.soa.com/}createPersonRequestTo" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPerson", propOrder = {
    "createPersonRequestTo"
})
public class CreatePerson {

    protected CreatePersonRequestTo createPersonRequestTo;

    /**
     * Gets the value of the createPersonRequestTo property.
     * 
     * @return
     *     possible object is
     *     {@link CreatePersonRequestTo }
     *     
     */
    public CreatePersonRequestTo getCreatePersonRequestTo() {
        return createPersonRequestTo;
    }

    /**
     * Sets the value of the createPersonRequestTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatePersonRequestTo }
     *     
     */
    public void setCreatePersonRequestTo(CreatePersonRequestTo value) {
        this.createPersonRequestTo = value;
    }

}
