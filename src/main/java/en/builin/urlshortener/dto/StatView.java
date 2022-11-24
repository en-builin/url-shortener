package en.builin.urlshortener.dto;

public interface StatView {

    String getLink();
    String getOriginal();
    int getRank();
    int getCount();
}
