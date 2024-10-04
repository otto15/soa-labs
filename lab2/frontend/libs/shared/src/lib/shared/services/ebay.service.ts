import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { BASE_URL, API_EBAY_PREFIX } from "../tokens";
import { Observable, of } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class EbayService {
    private readonly baseUrl = inject(BASE_URL);
    private readonly apiEbayPrefix = inject(API_EBAY_PREFIX);
    private readonly http = inject(HttpClient);

    public increase(percent: number): Observable<void> {
        // return this.http.post<void>(
        //     `${this.baseUrl}/${this.apiEbayPrefix}/price/increase/${percent}`,
        //     null,
        // )

        return of(undefined);
    }

    public decrease(percent: number): Observable<void> {
        // return this.http.post<void>(
        //     `${this.baseUrl}/${this.apiEbayPrefix}/price/decrease/${percent}`,
        //     null,
        // )

        return of(undefined);
    }
}