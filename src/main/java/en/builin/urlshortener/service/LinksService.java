package en.builin.urlshortener.service;

import en.builin.urlshortener.dto.LinkDto;
import en.builin.urlshortener.dto.OriginalDto;

public interface LinksService {

    LinkDto generateLink(OriginalDto originalDto);

    OriginalDto originalByShortLink(String shortLink);
}
