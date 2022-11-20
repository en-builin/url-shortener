package en.builin.urlshortener.repository;

import en.builin.urlshortener.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {

}
