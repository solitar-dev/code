import type { $Fetch, NitroFetchRequest } from "nitropack";
import { joinURL } from "ufo";

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
		return fetch<StatisticData>("/statistics");
	},
	async shortenUrl(body: UrlShortenerBody): Promise<UrlShortenerResponse> {
		return fetch<UrlShortenerResponse>("/create", {
			method: "post",
			body,
		});
	},
	async getLongUrl(shortCode: string): Promise<string> {
		const data = await fetch<{ originalUrl: string }>(joinURL("/forward", shortCode));

		return data.originalUrl;
	},
});
