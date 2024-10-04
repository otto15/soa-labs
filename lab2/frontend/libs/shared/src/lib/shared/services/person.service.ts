import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { API_PERSON_PREFIX, BASE_URL } from "../tokens";
import { Color, Person } from "../models";

@Injectable({
    providedIn: 'root'
})
export class PersonService {
    private readonly baseUrl = inject(BASE_URL);
    private readonly apiPersonPrefix = inject(API_PERSON_PREFIX);
    private readonly http = inject(HttpClient);

    public createPerson(payload: Person): Observable<void> {
        return this.http.post<void>(
            `${this.baseUrl}/${this.apiPersonPrefix}`,
            payload
        );
    }

    public getPerson(): Observable<Person[]> {
        // return this.http.get<Person[]>(
        //     `${this.baseUrl}/${this.apiPersonPrefix}`,
        // );

        return of([
            {
                name: "Татьяна Маркина",
                passportID: "1337 228",
                hairColor: Color.Black,
                eyeColor: Color.Green
            }
        ]);
    }
}