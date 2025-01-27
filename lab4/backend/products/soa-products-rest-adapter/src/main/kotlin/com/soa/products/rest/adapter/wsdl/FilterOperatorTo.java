
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for filterOperatorTo</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="filterOperatorTo">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="EQUAL"/>
 *     <enumeration value="LESS"/>
 *     <enumeration value="GREATER"/>
 *     <enumeration value="LESS_OR_EQUAL"/>
 *     <enumeration value="GREATER_OR_EQUAL"/>
 *     <enumeration value="SAME"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "filterOperatorTo")
@XmlEnum
public enum FilterOperatorTo {

    EQUAL,
    LESS,
    GREATER,
    LESS_OR_EQUAL,
    GREATER_OR_EQUAL,
    SAME;

    public String value() {
        return name();
    }

    public static FilterOperatorTo fromValue(String v) {
        return valueOf(v);
    }

}
