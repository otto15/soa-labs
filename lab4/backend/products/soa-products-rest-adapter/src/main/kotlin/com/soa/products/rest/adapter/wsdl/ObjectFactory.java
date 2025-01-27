
package com.soa.products.rest.adapter.wsdl;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.consumingwebservice.wsdl package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _CreatePerson_QNAME = new QName("http://service.products.soa.com/", "createPerson");
    private static final QName _CreatePersonResponse_QNAME = new QName("http://service.products.soa.com/", "createPersonResponse");
    private static final QName _CreateProduct_QNAME = new QName("http://service.products.soa.com/", "createProduct");
    private static final QName _CreateProductResponse_QNAME = new QName("http://service.products.soa.com/", "createProductResponse");
    private static final QName _DeleteProductById_QNAME = new QName("http://service.products.soa.com/", "deleteProductById");
    private static final QName _DeleteProductByIdResponse_QNAME = new QName("http://service.products.soa.com/", "deleteProductByIdResponse");
    private static final QName _GetAllProductPricesSum_QNAME = new QName("http://service.products.soa.com/", "getAllProductPricesSum");
    private static final QName _GetAllProductPricesSumResponse_QNAME = new QName("http://service.products.soa.com/", "getAllProductPricesSumResponse");
    private static final QName _GetMinPartNumberProduct_QNAME = new QName("http://service.products.soa.com/", "getMinPartNumberProduct");
    private static final QName _GetMinPartNumberProductResponse_QNAME = new QName("http://service.products.soa.com/", "getMinPartNumberProductResponse");
    private static final QName _GetProductById_QNAME = new QName("http://service.products.soa.com/", "getProductById");
    private static final QName _GetProductByIdResponse_QNAME = new QName("http://service.products.soa.com/", "getProductByIdResponse");
    private static final QName _GetProducts_QNAME = new QName("http://service.products.soa.com/", "getProducts");
    private static final QName _GetProductsResponse_QNAME = new QName("http://service.products.soa.com/", "getProductsResponse");
    private static final QName _GetProductsWithManufacturerCostLessThan_QNAME = new QName("http://service.products.soa.com/", "getProductsWithManufacturerCostLessThan");
    private static final QName _GetProductsWithManufacturerCostLessThanResponse_QNAME = new QName("http://service.products.soa.com/", "getProductsWithManufacturerCostLessThanResponse");
    private static final QName _UpdatePricesOfAllProducts_QNAME = new QName("http://service.products.soa.com/", "updatePricesOfAllProducts");
    private static final QName _UpdatePricesOfAllProductsResponse_QNAME = new QName("http://service.products.soa.com/", "updatePricesOfAllProductsResponse");
    private static final QName _UpdateProductById_QNAME = new QName("http://service.products.soa.com/", "updateProductById");
    private static final QName _UpdateProductByIdResponse_QNAME = new QName("http://service.products.soa.com/", "updateProductByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.consumingwebservice.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreatePerson }
     * 
     * @return
     *     the new instance of {@link CreatePerson }
     */
    public CreatePerson createCreatePerson() {
        return new CreatePerson();
    }

    /**
     * Create an instance of {@link CreatePersonResponse }
     * 
     * @return
     *     the new instance of {@link CreatePersonResponse }
     */
    public CreatePersonResponse createCreatePersonResponse() {
        return new CreatePersonResponse();
    }

    /**
     * Create an instance of {@link CreateProduct }
     * 
     * @return
     *     the new instance of {@link CreateProduct }
     */
    public CreateProduct createCreateProduct() {
        return new CreateProduct();
    }

    /**
     * Create an instance of {@link CreateProductResponse }
     * 
     * @return
     *     the new instance of {@link CreateProductResponse }
     */
    public CreateProductResponse createCreateProductResponse() {
        return new CreateProductResponse();
    }

    /**
     * Create an instance of {@link DeleteProductById }
     * 
     * @return
     *     the new instance of {@link DeleteProductById }
     */
    public DeleteProductById createDeleteProductById() {
        return new DeleteProductById();
    }

    /**
     * Create an instance of {@link DeleteProductByIdResponse }
     * 
     * @return
     *     the new instance of {@link DeleteProductByIdResponse }
     */
    public DeleteProductByIdResponse createDeleteProductByIdResponse() {
        return new DeleteProductByIdResponse();
    }

    /**
     * Create an instance of {@link GetAllProductPricesSum }
     * 
     * @return
     *     the new instance of {@link GetAllProductPricesSum }
     */
    public GetAllProductPricesSum createGetAllProductPricesSum() {
        return new GetAllProductPricesSum();
    }

    /**
     * Create an instance of {@link GetAllProductPricesSumResponse }
     * 
     * @return
     *     the new instance of {@link GetAllProductPricesSumResponse }
     */
    public GetAllProductPricesSumResponse createGetAllProductPricesSumResponse() {
        return new GetAllProductPricesSumResponse();
    }

    /**
     * Create an instance of {@link GetMinPartNumberProduct }
     * 
     * @return
     *     the new instance of {@link GetMinPartNumberProduct }
     */
    public GetMinPartNumberProduct createGetMinPartNumberProduct() {
        return new GetMinPartNumberProduct();
    }

    /**
     * Create an instance of {@link GetMinPartNumberProductResponse }
     * 
     * @return
     *     the new instance of {@link GetMinPartNumberProductResponse }
     */
    public GetMinPartNumberProductResponse createGetMinPartNumberProductResponse() {
        return new GetMinPartNumberProductResponse();
    }

    /**
     * Create an instance of {@link GetProductById }
     * 
     * @return
     *     the new instance of {@link GetProductById }
     */
    public GetProductById createGetProductById() {
        return new GetProductById();
    }

    /**
     * Create an instance of {@link GetProductByIdResponse }
     * 
     * @return
     *     the new instance of {@link GetProductByIdResponse }
     */
    public GetProductByIdResponse createGetProductByIdResponse() {
        return new GetProductByIdResponse();
    }

    /**
     * Create an instance of {@link GetProducts }
     * 
     * @return
     *     the new instance of {@link GetProducts }
     */
    public GetProducts createGetProducts() {
        return new GetProducts();
    }

    /**
     * Create an instance of {@link GetProductsResponse }
     * 
     * @return
     *     the new instance of {@link GetProductsResponse }
     */
    public GetProductsResponse createGetProductsResponse() {
        return new GetProductsResponse();
    }

    /**
     * Create an instance of {@link GetProductsWithManufacturerCostLessThan }
     * 
     * @return
     *     the new instance of {@link GetProductsWithManufacturerCostLessThan }
     */
    public GetProductsWithManufacturerCostLessThan createGetProductsWithManufacturerCostLessThan() {
        return new GetProductsWithManufacturerCostLessThan();
    }

    /**
     * Create an instance of {@link GetProductsWithManufacturerCostLessThanResponse }
     * 
     * @return
     *     the new instance of {@link GetProductsWithManufacturerCostLessThanResponse }
     */
    public GetProductsWithManufacturerCostLessThanResponse createGetProductsWithManufacturerCostLessThanResponse() {
        return new GetProductsWithManufacturerCostLessThanResponse();
    }

    /**
     * Create an instance of {@link UpdatePricesOfAllProducts }
     * 
     * @return
     *     the new instance of {@link UpdatePricesOfAllProducts }
     */
    public UpdatePricesOfAllProducts createUpdatePricesOfAllProducts() {
        return new UpdatePricesOfAllProducts();
    }

    /**
     * Create an instance of {@link UpdatePricesOfAllProductsResponse }
     * 
     * @return
     *     the new instance of {@link UpdatePricesOfAllProductsResponse }
     */
    public UpdatePricesOfAllProductsResponse createUpdatePricesOfAllProductsResponse() {
        return new UpdatePricesOfAllProductsResponse();
    }

    /**
     * Create an instance of {@link UpdateProductById }
     * 
     * @return
     *     the new instance of {@link UpdateProductById }
     */
    public UpdateProductById createUpdateProductById() {
        return new UpdateProductById();
    }

    /**
     * Create an instance of {@link UpdateProductByIdResponse }
     * 
     * @return
     *     the new instance of {@link UpdateProductByIdResponse }
     */
    public UpdateProductByIdResponse createUpdateProductByIdResponse() {
        return new UpdateProductByIdResponse();
    }

    /**
     * Create an instance of {@link ProductTo }
     * 
     * @return
     *     the new instance of {@link ProductTo }
     */
    public ProductTo createProductTo() {
        return new ProductTo();
    }

    /**
     * Create an instance of {@link CoordinatesTo }
     * 
     * @return
     *     the new instance of {@link CoordinatesTo }
     */
    public CoordinatesTo createCoordinatesTo() {
        return new CoordinatesTo();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     * @return
     *     the new instance of {@link LocalDate }
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link PersonTo }
     * 
     * @return
     *     the new instance of {@link PersonTo }
     */
    public PersonTo createPersonTo() {
        return new PersonTo();
    }

    /**
     * Create an instance of {@link CreatePersonRequestTo }
     * 
     * @return
     *     the new instance of {@link CreatePersonRequestTo }
     */
    public CreatePersonRequestTo createCreatePersonRequestTo() {
        return new CreatePersonRequestTo();
    }

    /**
     * Create an instance of {@link CreatePersonResponseTo }
     * 
     * @return
     *     the new instance of {@link CreatePersonResponseTo }
     */
    public CreatePersonResponseTo createCreatePersonResponseTo() {
        return new CreatePersonResponseTo();
    }

    /**
     * Create an instance of {@link CreateUpdateProductRequestTo }
     * 
     * @return
     *     the new instance of {@link CreateUpdateProductRequestTo }
     */
    public CreateUpdateProductRequestTo createCreateUpdateProductRequestTo() {
        return new CreateUpdateProductRequestTo();
    }

    /**
     * Create an instance of {@link CreateProductResponseTo }
     * 
     * @return
     *     the new instance of {@link CreateProductResponseTo }
     */
    public CreateProductResponseTo createCreateProductResponseTo() {
        return new CreateProductResponseTo();
    }

    /**
     * Create an instance of {@link ProductPriceSumTo }
     * 
     * @return
     *     the new instance of {@link ProductPriceSumTo }
     */
    public ProductPriceSumTo createProductPriceSumTo() {
        return new ProductPriceSumTo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreatePerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "createPerson")
    public JAXBElement<CreatePerson> createCreatePerson(CreatePerson value) {
        return new JAXBElement<>(_CreatePerson_QNAME, CreatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreatePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "createPersonResponse")
    public JAXBElement<CreatePersonResponse> createCreatePersonResponse(CreatePersonResponse value) {
        return new JAXBElement<>(_CreatePersonResponse_QNAME, CreatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProduct }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateProduct }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "createProduct")
    public JAXBElement<CreateProduct> createCreateProduct(CreateProduct value) {
        return new JAXBElement<>(_CreateProduct_QNAME, CreateProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProductResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateProductResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "createProductResponse")
    public JAXBElement<CreateProductResponse> createCreateProductResponse(CreateProductResponse value) {
        return new JAXBElement<>(_CreateProductResponse_QNAME, CreateProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProductById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteProductById }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "deleteProductById")
    public JAXBElement<DeleteProductById> createDeleteProductById(DeleteProductById value) {
        return new JAXBElement<>(_DeleteProductById_QNAME, DeleteProductById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProductByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteProductByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "deleteProductByIdResponse")
    public JAXBElement<DeleteProductByIdResponse> createDeleteProductByIdResponse(DeleteProductByIdResponse value) {
        return new JAXBElement<>(_DeleteProductByIdResponse_QNAME, DeleteProductByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductPricesSum }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllProductPricesSum }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getAllProductPricesSum")
    public JAXBElement<GetAllProductPricesSum> createGetAllProductPricesSum(GetAllProductPricesSum value) {
        return new JAXBElement<>(_GetAllProductPricesSum_QNAME, GetAllProductPricesSum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductPricesSumResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllProductPricesSumResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getAllProductPricesSumResponse")
    public JAXBElement<GetAllProductPricesSumResponse> createGetAllProductPricesSumResponse(GetAllProductPricesSumResponse value) {
        return new JAXBElement<>(_GetAllProductPricesSumResponse_QNAME, GetAllProductPricesSumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMinPartNumberProduct }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetMinPartNumberProduct }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getMinPartNumberProduct")
    public JAXBElement<GetMinPartNumberProduct> createGetMinPartNumberProduct(GetMinPartNumberProduct value) {
        return new JAXBElement<>(_GetMinPartNumberProduct_QNAME, GetMinPartNumberProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMinPartNumberProductResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetMinPartNumberProductResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getMinPartNumberProductResponse")
    public JAXBElement<GetMinPartNumberProductResponse> createGetMinPartNumberProductResponse(GetMinPartNumberProductResponse value) {
        return new JAXBElement<>(_GetMinPartNumberProductResponse_QNAME, GetMinPartNumberProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProductById }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getProductById")
    public JAXBElement<GetProductById> createGetProductById(GetProductById value) {
        return new JAXBElement<>(_GetProductById_QNAME, GetProductById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProductByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getProductByIdResponse")
    public JAXBElement<GetProductByIdResponse> createGetProductByIdResponse(GetProductByIdResponse value) {
        return new JAXBElement<>(_GetProductByIdResponse_QNAME, GetProductByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProducts }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProducts }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getProducts")
    public JAXBElement<GetProducts> createGetProducts(GetProducts value) {
        return new JAXBElement<>(_GetProducts_QNAME, GetProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProductsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getProductsResponse")
    public JAXBElement<GetProductsResponse> createGetProductsResponse(GetProductsResponse value) {
        return new JAXBElement<>(_GetProductsResponse_QNAME, GetProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsWithManufacturerCostLessThan }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProductsWithManufacturerCostLessThan }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getProductsWithManufacturerCostLessThan")
    public JAXBElement<GetProductsWithManufacturerCostLessThan> createGetProductsWithManufacturerCostLessThan(GetProductsWithManufacturerCostLessThan value) {
        return new JAXBElement<>(_GetProductsWithManufacturerCostLessThan_QNAME, GetProductsWithManufacturerCostLessThan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsWithManufacturerCostLessThanResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProductsWithManufacturerCostLessThanResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "getProductsWithManufacturerCostLessThanResponse")
    public JAXBElement<GetProductsWithManufacturerCostLessThanResponse> createGetProductsWithManufacturerCostLessThanResponse(GetProductsWithManufacturerCostLessThanResponse value) {
        return new JAXBElement<>(_GetProductsWithManufacturerCostLessThanResponse_QNAME, GetProductsWithManufacturerCostLessThanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePricesOfAllProducts }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdatePricesOfAllProducts }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "updatePricesOfAllProducts")
    public JAXBElement<UpdatePricesOfAllProducts> createUpdatePricesOfAllProducts(UpdatePricesOfAllProducts value) {
        return new JAXBElement<>(_UpdatePricesOfAllProducts_QNAME, UpdatePricesOfAllProducts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePricesOfAllProductsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdatePricesOfAllProductsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "updatePricesOfAllProductsResponse")
    public JAXBElement<UpdatePricesOfAllProductsResponse> createUpdatePricesOfAllProductsResponse(UpdatePricesOfAllProductsResponse value) {
        return new JAXBElement<>(_UpdatePricesOfAllProductsResponse_QNAME, UpdatePricesOfAllProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProductById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateProductById }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "updateProductById")
    public JAXBElement<UpdateProductById> createUpdateProductById(UpdateProductById value) {
        return new JAXBElement<>(_UpdateProductById_QNAME, UpdateProductById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProductByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateProductByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.products.soa.com/", name = "updateProductByIdResponse")
    public JAXBElement<UpdateProductByIdResponse> createUpdateProductByIdResponse(UpdateProductByIdResponse value) {
        return new JAXBElement<>(_UpdateProductByIdResponse_QNAME, UpdateProductByIdResponse.class, null, value);
    }

}
