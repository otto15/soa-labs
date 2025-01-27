
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for colorTo</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="colorTo">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="YELLOW"/>
 *     <enumeration value="WHITE"/>
 *     <enumeration value="BROWN"/>
 *     <enumeration value="GREEN"/>
 *     <enumeration value="RED"/>
 *     <enumeration value="BLACK"/>
 *     <enumeration value="ORANGE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "colorTo")
@XmlEnum
public enum ColorTo {

    YELLOW,
    WHITE,
    BROWN,
    GREEN,
    RED,
    BLACK,
    ORANGE;

    public String value() {
        return name();
    }

    public static ColorTo fromValue(String v) {
        return valueOf(v);
    }

}
