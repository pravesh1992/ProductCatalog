package com.contentserv.productcatalog.repositores;

import com.contentserv.productcatalog.repositores.db.objects.DBProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<DBProduct, Integer> {
}