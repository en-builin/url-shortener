package en.builin.urlshortener.service;

import en.builin.urlshortener.dto.LinkDto;
import en.builin.urlshortener.dto.OriginalDto;
import en.builin.urlshortener.exception.LinkNotFoundException;
import en.builin.urlshortener.model.Link;
import en.builin.urlshortener.model.Stat;
import en.builin.urlshortener.repository.LinkRepository;
import en.builin.urlshortener.util.KeyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LinksServiceImpl implements LinksService {

    private final LinkRepository linkRepository;
    private final KeyConverter keyConverter;
    private final StatsService statsService;

    @Override
    public LinkDto generateLink(OriginalDto original) {

        Link link = linkRepository.save(getLinkFromOriginal(original));

        String key = keyConverter.idToKey(link.getId());
        log.debug("Converted key {} from id {}", key, link.getId());

        statsService.createStat(new Stat(link, key));

        return new LinkDto(key);
    }

    @Override
    public OriginalDto originalByShortLink(String shortLink) {

        long id = keyConverter.keyToId(shortLink);
        log.debug("Converted short link {} to id {}", shortLink, id);

        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new LinkNotFoundException(shortLink));

        String url = link.getUrl();
        log.debug("Found url {} by id {}", url, id);

        return new OriginalDto(url);
    }

    private Link getLinkFromOriginal(OriginalDto original) {
        return Link.builder()
                .url(original.getOriginal())
                .build();
    }
}
