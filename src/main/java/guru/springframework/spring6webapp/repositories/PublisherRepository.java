package guru.springframework.spring6webapp.repositories;

import guru.springframework.spring6webapp.dto.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
