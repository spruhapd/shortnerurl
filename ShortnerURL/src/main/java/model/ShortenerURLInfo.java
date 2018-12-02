package model;

public class ShortenerURLInfo {
	String url;
	Integer redirectType = 302;
	String shortUrl;
	int counter = 0;

	public ShortenerURLInfo() {
	}

	public ShortenerURLInfo(String url, Integer redirectType, String shortUrl, int counter) {
		this.url = url;
		this.redirectType = redirectType;
		this.shortUrl = shortUrl;
		this.counter = counter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(Integer redirectType) {
		this.redirectType = redirectType;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
