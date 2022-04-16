package ch.zli.onenote.entities.repositories;

import ch.zli.onenote.entities.Text;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends CrudRepository<Text, Integer> {
}
