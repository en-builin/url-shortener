package en.builin.urlshortener.dto;

import en.builin.urlshortener.util.WebUtils;
import lombok.Getter;

import java.io.Serializable;

public class LinkDto implements Serializable {

    @Getter
    private final String link;

    public LinkDto(String key) {
        this.link = WebUtils.LINKS_ENDPOINT + key;
    }
}
