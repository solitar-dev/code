import dayjs from "dayjs";

const expireUnits = ["second", "minute", "hour", "day", "week", "month", "year"] as const;

export type ExpireUnit = (typeof expireUnits)[number];

export function generateExpireTime(expireTime: number, expireUnit: ExpireUnit): string {
	return dayjs().add(expireTime, expireUnit).toISOString();
}
