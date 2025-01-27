
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for priceOperationTo</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="priceOperationTo">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="INCREASE"/>
 *     <enumeration value="DECREASE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "priceOperationTo")
@XmlEnum
public enum PriceOperationTo {

    INCREASE,
    DECREASE;

    public String value() {
        return name();
    }

    public static PriceOperationTo fromValue(String v) {
        return valueOf(v);
    }

}
