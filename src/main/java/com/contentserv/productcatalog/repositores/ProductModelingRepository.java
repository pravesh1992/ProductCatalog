package com.contentserv.productcatalog.repositores;

import com.contentserv.productcatalog.repositores.db.objects.DBProductModelingAttribute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductModelingRepository extends CrudRepository<DBProductModelingAttribute, Integer> {

  @Query(value = "select * from product_catalog.product_modeling_attributes where product_category_id=:id", nativeQuery = true)
  Collection<DBProductModelingAttribute> findAllProductModelingAttributes(@Param("id") Integer id);
}