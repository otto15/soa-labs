
package com.soa.products.rest.adapter.wsdl;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for criteriaTo</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="criteriaTo">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="ID"/>
 *     <enumeration value="NAME"/>
 *     <enumeration value="COORDINATES"/>
 *     <enumeration value="CREATION_DATE"/>
 *     <enumeration value="PRICE"/>
 *     <enumeration value="PART_NUMBER"/>
 *     <enumeration value="MANUFACTURER_COST"/>
 *     <enumeration value="UNIT_OF_MEASURE"/>
 *     <enumeration value="OWNER_PASSPORT_ID"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "criteriaTo")
@XmlEnum
public enum CriteriaTo {

    ID,
    NAME,
    COORDINATES,
    CREATION_DATE,
    PRICE,
    PART_NUMBER,
    MANUFACTURER_COST,
    UNIT_OF_MEASURE,
    OWNER_PASSPORT_ID;

    public String value() {
        return name();
    }

    public static CriteriaTo fromValue(String v) {
        return valueOf(v);
    }

}
