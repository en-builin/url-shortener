package en.builin.urlshortener.service;

import en.builin.urlshortener.dto.StatView;
import en.builin.urlshortener.model.Stat;
import en.builin.urlshortener.repository.StatRepository;
import en.builin.urlshortener.util.KeyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService {

    private final StatRepository statRepository;
    private final KeyConverter keyConverter;

    @Override
    public List<StatView> getStatViews(int page, int count) {
        return statRepository.getStatViews(Pageable.ofSize(count).withPage(page));
    }

    @Override
    public StatView getStatView(String shortLink) {
        return statRepository.getStatViewByShortLink(keyConverter.keyToId(shortLink));
    }

    @Override
    public Stat createStat(Stat stat) {
        return statRepository.save(stat);
    }

    @Override
    public void countRedirect(String shortLink) {
        log.debug("Count redirect to {}", shortLink);
        statRepository.countRedirect(keyConverter.keyToId(shortLink));
    }
}
