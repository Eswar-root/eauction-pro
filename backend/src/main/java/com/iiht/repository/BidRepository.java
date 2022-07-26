package com.iiht.repository;

import com.iiht.entity.BidEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends MongoRepository<BidEntity, String> {

    @Query("{productId: ?0, buyerEmail: ?1}")
    BidEntity findByProductIdAndBuyerEmail(String productId, String buyerEmail);

    @Query(value = "{productId : ?0}", count = true)
    Integer countNumberOfBidsForProduct(String productId);

    @Query(value = "{productId: ?0}", sort = "{bidAmount: -1}")
    List<BidEntity> findAllByProductId(String productId);

}
