package urlprocess;

import java.util.HashMap;
import java.util.Map;

public abstract class ShortenerURLCache<E> {
	protected Map<String, E> urlContainer = new HashMap<>();

	protected boolean containsKey(String key) {
		return urlContainer.containsKey(key);
	}

	protected E getObject(String key) {
		return urlContainer.get(key);
	}

	protected void put(String key, E object) {
		urlContainer.put(key, object);
	}
}
