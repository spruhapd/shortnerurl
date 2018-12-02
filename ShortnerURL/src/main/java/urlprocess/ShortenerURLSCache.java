package urlprocess;

import model.ShortenerURLInfo;

public class ShortenerURLSCache extends ShortenerURLCache<ShortenerURLInfo> {
	
	public ShortenerURLInfo getShortUrl(String shortUrl) {
		for (String key : urlContainer.keySet())
			if (urlContainer.get(key).getShortUrl().equals(shortUrl))
				return urlContainer.get(key);
		return null;
	}

	
	public void updateRedirectType(String url, int redirectType) {
		urlContainer.get(url).setRedirectType(redirectType);
	}
}
