package en.builin.urlshortener.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stats", indexes = @Index(columnList = "shortLink"))
@Getter
@Setter
@NoArgsConstructor
public class Stat implements Serializable {

    @Id
    Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    Link link;

    String shortLink;

    Integer count;

    public Stat(Link link, String shortLink) {
        this.link = link;
        this.shortLink = shortLink;
        this.count = 0;
    }
}
