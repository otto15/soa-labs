import { Coordinates } from "./coordinates";
import { Owner } from "./owner";
import { UnitOfMeasureType } from "./unit-of-measure.enum";

export interface Product {
    price: number;
    name: string;
    coordinates: Coordinates;
    manufactureCost: number;
    partNumber: string;
    id: number;
    owner: Owner;
    creationDate: string;
    unitOfMeasure: UnitOfMeasureType;
}