package en.builin.urlshortener.service;

import en.builin.urlshortener.dto.LinkDto;
import en.builin.urlshortener.dto.OriginalDto;
import en.builin.urlshortener.exception.LinkNotFoundException;
import en.builin.urlshortener.model.Link;
import en.builin.urlshortener.repository.LinkRepository;
import en.builin.urlshortener.util.KeyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LinksServiceImpl implements LinksService {

    private final LinkRepository repository;
    private final KeyConverter keyConverter;

    @Override
    public LinkDto generateLink(OriginalDto originalDto) {

        Link link = repository.save(getLinkFromDto(originalDto));

        String key = keyConverter.idToKey(link.getId());
        log.debug("Converted key {} from id {}", key, link.getId());

        return new LinkDto(key);
    }

    @Override
    public OriginalDto getLink(String key) {

        long id = keyConverter.keyToId(key);
        log.debug("Converted key {} to id{}", key, id);

        Link link = repository.findById(id)
                .orElseThrow(() -> new LinkNotFoundException(key));

        String url = link.getUrl();
        log.debug("Found url {} by id {}", url, id);

        return new OriginalDto(url);
    }

    private Link getLinkFromDto(OriginalDto originalDto) {
        return Link.builder()
                .url(originalDto.getOriginal())
                .build();
    }
}
