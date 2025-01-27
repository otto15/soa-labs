
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for unitOfMeasureTo</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="unitOfMeasureTo">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="KILOGRAMS"/>
 *     <enumeration value="METERS"/>
 *     <enumeration value="MILLILITERS"/>
 *     <enumeration value="MILLIGRAMS"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "unitOfMeasureTo")
@XmlEnum
public enum UnitOfMeasureTo {

    KILOGRAMS,
    METERS,
    MILLILITERS,
    MILLIGRAMS;

    public String value() {
        return name();
    }

    public static UnitOfMeasureTo fromValue(String v) {
        return valueOf(v);
    }

}
