
package com.soa.products.rest.adapter.wsdl;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePricesOfAllProducts complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="updatePricesOfAllProducts">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="priceOperation" type="{http://service.products.soa.com/}priceOperationTo" minOccurs="0"/>
 *         <element name="percent" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
@XmlType(name = "updatePricesOfAllProducts", propOrder = {
    "priceOperation",
    "percent"
})
public class UpdatePricesOfAllProducts {

    @XmlSchemaType(name = "string")
    protected PriceOperationTo priceOperation;
    protected BigDecimal percent;

    /**
     * Gets the value of the priceOperation property.
     * 
     * @return
     *     possible object is
     *     {@link PriceOperationTo }
     *     
     */
    public PriceOperationTo getPriceOperation() {
        return priceOperation;
    }

    /**
     * Sets the value of the priceOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceOperationTo }
     *     
     */
    public void setPriceOperation(PriceOperationTo value) {
        this.priceOperation = value;
    }

    /**
     * Gets the value of the percent property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     * Sets the value of the percent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercent(BigDecimal value) {
        this.percent = value;
    }

}
