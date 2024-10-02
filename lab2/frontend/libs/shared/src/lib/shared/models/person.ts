import { EyeColorType } from "./eye-color.enum";
import { HairColorType } from "./hair-color.enum";

export interface Person {
    name: string;
    passportId: string;
    eyeColor: EyeColorType;
    hairColor: HairColorType;
}