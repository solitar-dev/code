import dayjs from "dayjs";

export const expireTimeUnits = [
	"second",
	"minute",
	"hour",
	"day",
	"week",
	"month",
	"year",
] as const;

export type ExpireUnit = (typeof expireTimeUnits)[number];

export function generateExpireTime(expireTime: number, expireUnit: ExpireUnit): string {
	return dayjs().add(expireTime, expireUnit).toISOString();
}
