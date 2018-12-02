package urlprocess;

import model.ShortenerURLInfo;

public class ShortenerURLProvider {
	ShortenerURLSCache shortUrlsCache;

	public ShortenerURLProvider() {
		createTables();
		shortUrlsCache = ShortenerURLQueryExecutor.loadURLSCache();
	}

	/**
	 * Creates URL table in database on startup.
	 */
	private void createTables() {
		String ddl = "CREATE TABLE IF NOT EXISTS urls" +
				"(url TEXT PRIMARY KEY NOT NULL," +
				"redirect_type INT DEFAULT 302," +
				"short_url TEXT UNIQUE NOT NULL," +
				"counter INT DEFAULT 0)";

		ShortenerURLQueryExecutor.update(ddl);
	}

	public boolean shortUrlExists(String shortUrl) {
		return shortUrlsCache.getShortUrl(shortUrl) != null;
	}

	public ShortenerURLInfo getUrlInfo(String url) {
		return shortUrlsCache.getObject(url);
	}

	public ShortenerURLInfo getUrlInfoByShortUrl(String shortUrl) {
		return shortUrlsCache.getShortUrl(shortUrl);
	}

	public void createURL(ShortenerURLInfo urlInfo) {
		shortUrlsCache.put(urlInfo.getUrl(), urlInfo);
		ShortenerURLQueryExecutor.update("insert into URLS (URL, SHORT_URL, REDIRECT_TYPE) " +
				"values (\"" + urlInfo.getUrl() + "\",\"" + urlInfo.getShortUrl() + "\",\"" + urlInfo
				.getRedirectType() + "\")");
	}

	public void updateURL(ShortenerURLInfo urlInfo) {
		shortUrlsCache.updateRedirectType(urlInfo.getUrl(), urlInfo.getRedirectType());
		ShortenerURLQueryExecutor.update("update URLS set REDIRECT_TYPE = \"" + urlInfo.getRedirectType() + "\"" +
				"where url = \"" + urlInfo.getUrl() + "\"");
	}

	public void counterInc(String url) {
		int count = shortUrlsCache.getObject(url).getCounter() + 1;
		shortUrlsCache.getObject(url).setCounter(count);
		ShortenerURLQueryExecutor.update("update URLS set COUNTER = \"" + count + "\" where URL = \"" + url + "\"");
	}
}
