
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for sortTypeTo</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="sortTypeTo">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="ASC"/>
 *     <enumeration value="DESC"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "sortTypeTo")
@XmlEnum
public enum SortTypeTo {

    ASC,
    DESC;

    public String value() {
        return name();
    }

    public static SortTypeTo fromValue(String v) {
        return valueOf(v);
    }

}
