
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateProductById complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="updateProductById">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         <element name="createUpdateProductRequestTo" type="{http://service.products.soa.com/}createUpdateProductRequestTo" minOccurs="0"/>
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
@XmlType(name = "updateProductById", propOrder = {
    "id",
    "createUpdateProductRequestTo"
})
public class UpdateProductById {

    protected long id;
    protected CreateUpdateProductRequestTo createUpdateProductRequestTo;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the createUpdateProductRequestTo property.
     * 
     * @return
     *     possible object is
     *     {@link CreateUpdateProductRequestTo }
     *     
     */
    public CreateUpdateProductRequestTo getCreateUpdateProductRequestTo() {
        return createUpdateProductRequestTo;
    }

    /**
     * Sets the value of the createUpdateProductRequestTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateUpdateProductRequestTo }
     *     
     */
    public void setCreateUpdateProductRequestTo(CreateUpdateProductRequestTo value) {
        this.createUpdateProductRequestTo = value;
    }

}
