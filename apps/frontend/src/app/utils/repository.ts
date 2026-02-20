import type { $Fetch, NitroFetchRequest } from "nitropack";

type StatisticData = {
	totalLinks: number;
	totalClicks: number;
};

type UrlShortenerResponse = {
	originalUrl: string;
	shortCode: string;
};

export type UrlShortenerBody = {
	url: string;
	alias?: string;
	expiresTime?: string;
};

export const repository = <T>(fetch: $Fetch<T, NitroFetchRequest>) => ({
	async getStatisticData(): Promise<StatisticData> {
		return fetch("/statistics");
	},
	async shortenUrl(body: UrlShortenerBody): Promise<UrlShortenerResponse> {
		return fetch("/create", {
			method: "post",
			body,
		});
	},
});
