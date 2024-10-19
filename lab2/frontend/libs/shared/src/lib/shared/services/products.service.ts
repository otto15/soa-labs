import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { API_PRODUCTS_PREFIX, BASE_URL, BASE_URL_PRODUCTS } from "../tokens";
import { CreateProductPayload, Person, PricesSum, Product, UnitOfMeasure } from "../models";

@Injectable({
    providedIn: 'root'
})
export class ProductsService {
    private readonly baseUrl = inject(BASE_URL_PRODUCTS);
    private readonly apiProductsPrefix = inject(API_PRODUCTS_PREFIX);
    private readonly http = inject(HttpClient);

    public createProduct(payload: CreateProductPayload): Observable<number> {
        return this.http.post<number>(
            `${this.baseUrl}/${this.apiProductsPrefix}`,
            payload
        );
    }

    public updateProduct(
        productId: number, 
        payload: CreateProductPayload
    ): Observable<number> {
        return this.http.put<number>(
            `${this.baseUrl}/${this.apiProductsPrefix}/${productId}`,
            payload
        );
    }

    public getProducts(): Observable<Product[]> {
        return this.http.get<Product[]>(
            `${this.baseUrl}/${this.apiProductsPrefix}`,
        );
    }

    public deleteProduct(productId: number): Observable<void> {
        return this.http.delete<void>(
            `${this.baseUrl}/${this.apiProductsPrefix}/${productId}`,
        );
    }

    public getPricesSum(): Observable<PricesSum> {
        return this.http.get<PricesSum>(
            `${this.baseUrl}/${this.apiProductsPrefix}/price/sum`
        );
    }

    public getMinimalProduct(): Observable<Product> {
        return this.http.get<Product>(
            `${this.baseUrl}/${this.apiProductsPrefix}/part-number/min`
        )
    }

    public getLessThanProduct(
        cost: number,
        page: number,
        size: number,
    ): Observable<Product[]> {
        return this.http.get<Product[]>(
            `${this.baseUrl}/${this.apiProductsPrefix}/manufacture-cost/less-than?cost=${cost}&page=${page}&size=${size}`
        )
    }

    public getById(id: number): Observable<Product> {
        return this.http.get<Product>(
            `${this.baseUrl}/${this.apiProductsPrefix}/${id}`,
        );
    }
}