
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createUpdateProductRequestTo complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="createUpdateProductRequestTo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="coordinates" type="{http://service.products.soa.com/}coordinatesTo" minOccurs="0"/>
 *         <element name="manufacturerCost" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ownerPassportId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="partNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="price" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         <element name="unitOfMeasure" type="{http://service.products.soa.com/}unitOfMeasureTo" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createUpdateProductRequestTo", propOrder = {
    "coordinates",
    "manufacturerCost",
    "name",
    "ownerPassportId",
    "partNumber",
    "price",
    "unitOfMeasure"
})
public class CreateUpdateProductRequestTo {

    protected CoordinatesTo coordinates;
    protected Long manufacturerCost;
    protected String name;
    protected String ownerPassportId;
    protected String partNumber;
    protected Long price;
    @XmlSchemaType(name = "string")
    protected UnitOfMeasureTo unitOfMeasure;

    /**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link CoordinatesTo }
     *     
     */
    public CoordinatesTo getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinatesTo }
     *     
     */
    public void setCoordinates(CoordinatesTo value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the manufacturerCost property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getManufacturerCost() {
        return manufacturerCost;
    }

    /**
     * Sets the value of the manufacturerCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setManufacturerCost(Long value) {
        this.manufacturerCost = value;
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
     * Gets the value of the ownerPassportId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerPassportId() {
        return ownerPassportId;
    }

    /**
     * Sets the value of the ownerPassportId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerPassportId(String value) {
        this.ownerPassportId = value;
    }

    /**
     * Gets the value of the partNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * Sets the value of the partNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNumber(String value) {
        this.partNumber = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPrice(Long value) {
        this.price = value;
    }

    /**
     * Gets the value of the unitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link UnitOfMeasureTo }
     *     
     */
    public UnitOfMeasureTo getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets the value of the unitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitOfMeasureTo }
     *     
     */
    public void setUnitOfMeasure(UnitOfMeasureTo value) {
        this.unitOfMeasure = value;
    }

}
