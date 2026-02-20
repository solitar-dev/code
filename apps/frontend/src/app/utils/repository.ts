import type { $Fetch, NitroFetchRequest } from "nitropack";

type StatisticData = {
	totalLinks: number;
	totalClicks: number;
};

export const repository = <T>(fetch: $Fetch<T, NitroFetchRequest>) => ({
	async getStatisticData(): Promise<StatisticData> {
		return fetch("/statistics");
	},
});
