package com.contentserv.productcatalog.repositores;

import com.contentserv.productcatalog.repositores.db.objects.DBProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends CrudRepository<DBProductCategory, Integer> {
  DBProductCategory findOneByName(String name);
}