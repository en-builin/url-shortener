package en.builin.urlshortener.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "links")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2048)
    private String url;
}
