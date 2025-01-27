
package com.soa.products.rest.adapter.wsdl;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getProducts complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="getProducts">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="page" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="size" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="sortCriteriaList" type="{http://service.products.soa.com/}criteriaTo" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="sortTypeList" type="{http://service.products.soa.com/}sortTypeTo" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="filterCriteriaList" type="{http://service.products.soa.com/}criteriaTo" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="filterOperators" type="{http://service.products.soa.com/}filterOperatorTo" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="filterValues" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "getProducts", propOrder = {
    "page",
    "size",
    "sortCriteriaList",
    "sortTypeList",
    "filterCriteriaList",
    "filterOperators",
    "filterValues"
})
public class GetProducts {

    protected int page;
    protected int size;
    @XmlSchemaType(name = "string")
    protected List<CriteriaTo> sortCriteriaList;
    @XmlSchemaType(name = "string")
    protected List<SortTypeTo> sortTypeList;
    @XmlSchemaType(name = "string")
    protected List<CriteriaTo> filterCriteriaList;
    @XmlSchemaType(name = "string")
    protected List<FilterOperatorTo> filterOperators;
    protected List<String> filterValues;

    /**
     * Gets the value of the page property.
     * 
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     */
    public void setPage(int value) {
        this.page = value;
    }

    /**
     * Gets the value of the size property.
     * 
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     */
    public void setSize(int value) {
        this.size = value;
    }

    /**
     * Gets the value of the sortCriteriaList property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sortCriteriaList property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSortCriteriaList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CriteriaTo }
     * </p>
     * 
     * 
     * @return
     *     The value of the sortCriteriaList property.
     */
    public List<CriteriaTo> getSortCriteriaList() {
        if (sortCriteriaList == null) {
            sortCriteriaList = new ArrayList<>();
        }
        return this.sortCriteriaList;
    }

    /**
     * Gets the value of the sortTypeList property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sortTypeList property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSortTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SortTypeTo }
     * </p>
     * 
     * 
     * @return
     *     The value of the sortTypeList property.
     */
    public List<SortTypeTo> getSortTypeList() {
        if (sortTypeList == null) {
            sortTypeList = new ArrayList<>();
        }
        return this.sortTypeList;
    }

    /**
     * Gets the value of the filterCriteriaList property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filterCriteriaList property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFilterCriteriaList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CriteriaTo }
     * </p>
     * 
     * 
     * @return
     *     The value of the filterCriteriaList property.
     */
    public List<CriteriaTo> getFilterCriteriaList() {
        if (filterCriteriaList == null) {
            filterCriteriaList = new ArrayList<>();
        }
        return this.filterCriteriaList;
    }

    /**
     * Gets the value of the filterOperators property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filterOperators property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFilterOperators().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FilterOperatorTo }
     * </p>
     * 
     * 
     * @return
     *     The value of the filterOperators property.
     */
    public List<FilterOperatorTo> getFilterOperators() {
        if (filterOperators == null) {
            filterOperators = new ArrayList<>();
        }
        return this.filterOperators;
    }

    /**
     * Gets the value of the filterValues property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filterValues property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFilterValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the filterValues property.
     */
    public List<String> getFilterValues() {
        if (filterValues == null) {
            filterValues = new ArrayList<>();
        }
        return this.filterValues;
    }

}
