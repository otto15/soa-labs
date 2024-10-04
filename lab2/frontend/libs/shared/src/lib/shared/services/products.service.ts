import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { API_PRODUCTS_PREFIX, BASE_URL } from "../tokens";
import { CreateProductPayload, PricesSum, Product, UnitOfMeasure } from "../models";

@Injectable({
    providedIn: 'root'
})
export class ProductsService {
    private readonly baseUrl = inject(BASE_URL);
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
        // return this.http.put<number>(
        //     `${this.baseUrl}/${this.apiProductsPrefix}/${productId}`,
        //     payload
        // );

        return of(1);
    }

    public getProducts(): Observable<Product[]> {
        // return this.http.get<Person[]>(
        //     `${this.baseUrl}/${this.apiProductsPrefix}`,
        // );

        // price: number;
        // name: string;
        // coordinates: Coordinates;
        // manufactureCost: number;
        // partNumber: string;
        // id: number;
        // owner: Owner;
        // creationDate: string;

        return of([
            {
                price: 10,
                name: 'Тестовый продукт',
                coordinates: {
                    x: 1,
                    y: 2,
                },
                manufacturerCost: 10,
                partNumber: '123456271782',
                id: 1,
                owner: {
                    name: 'Татьяна Маркина',
                    passportID: '1337228228',
                },
                createdDate: '2024-06-09',
                unitOfMeasure: UnitOfMeasure.Meters,
            }
        ]);
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