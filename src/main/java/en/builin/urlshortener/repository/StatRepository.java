package en.builin.urlshortener.repository;

import en.builin.urlshortener.dto.StatView;
import en.builin.urlshortener.model.Link;
import en.builin.urlshortener.model.Stat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StatRepository extends JpaRepository<Stat, Link> {

    @Transactional
    @Modifying
    @Query("update Stat s set s.count = s.count + 1 where s.id = :id")
    void countRedirect(long id);

    @Query(nativeQuery = true, value =
            "select r.link as link, l.url as original, r.rank as rank, r.count as count " +
            "from (select s.id, s.short_link as link, s.count, " +
            "       rank() over(order by s.count desc) as rank " +
            "from stats as s) r " +
            "inner join links as l on r.id = l.id " +
            "order by r.count desc")
    List<StatView> getStatViews(Pageable pageable);

    @Query(nativeQuery = true, value =
            "select r.link as link, l.url as original, r.rank as rank, r.count as count " +
            "from (select s.id, s.short_link as link, s.count, " +
            "       rank() over(order by s.count desc) as rank " +
            "from stats as s) r " +
            "inner join links as l on r.id = l.id and r.id = :id")
    StatView getStatViewByShortLink(long id);
}
