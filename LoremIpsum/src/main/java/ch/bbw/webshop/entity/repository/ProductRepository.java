package ch.bbw.webshop.entity.repository;

import ch.bbw.webshop.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lewin Gerber
 * @version 21.11.2020
 * webshop
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> { }
