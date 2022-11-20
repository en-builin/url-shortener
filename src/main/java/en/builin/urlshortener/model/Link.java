package en.builin.urlshortener.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "links")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2048)
    private String url;
}
