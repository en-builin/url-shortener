package en.builin.urlshortener.util;

public interface KeyConverter {

    long keyToId(String key);

    String idToKey(long id);
}
