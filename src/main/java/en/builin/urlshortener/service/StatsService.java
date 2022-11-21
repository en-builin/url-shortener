package en.builin.urlshortener.service;

import en.builin.urlshortener.dto.StatView;
import en.builin.urlshortener.model.Stat;

import java.util.List;

public interface StatsService {

    List<StatView> getStatViews(int page, int count);

    StatView getStatView(String shortLink);

    Stat createStat(Stat stat);

    void countRedirect(String shortLink);
}
