import { PerfumePrice } from "../../types/types";

export const perfumer: Array<{ name: string }> = [
    { name: "Тинктури" },
    { name: "Сирупи" },
    { name: "Сокови" },
    { name: "Слатки" },
    { name: "Мармалади" },
    { name: "Мелеми" }
];

export const gender: Array<{ name: string }> = [{ name: "male" }, { name: "female" }];

export const price: Array<PerfumePrice> = [
    { id: 1, name: "Сите", array: [1, 9999] },
    { id: 2, name: "299 - 599 MKD", array: [299, 599] },
    { id: 3, name: "599 - 999 MKD", array: [599, 999] },
    { id: 4, name: "999+ MKD", array: [999, 9999] }
];
