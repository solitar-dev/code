import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import dayjs from "dayjs";
import { generateExpireTime } from "../../app/utils/time";

describe("generateExpireTime", () => {
	const MOCK_NOW = "2026-02-14T12:00:00Z";

	beforeEach(() => {
		vi.useFakeTimers();
		vi.setSystemTime(new Date(MOCK_NOW));
	});

	afterEach(() => {
		vi.useRealTimers();
	});

	it("should add 1 hour to the current time", () => {
		const result = generateExpireTime(1, "hour");
		const expected = dayjs(MOCK_NOW).add(1, "hour").toISOString();

		expect(result).toBe(expected);
	});

	it("should add 7 days to the current time", () => {
		const result = generateExpireTime(7, "day");
		const expected = dayjs(MOCK_NOW).add(7, "day").toISOString();

		expect(result).toBe(expected);
	});

	it("should return an ISO string format", () => {
		const result = generateExpireTime(10, "minute");

		expect(result).toMatch(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}.\d{3}Z$/);
	});
});
