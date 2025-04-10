package com.coolserver.server.storage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageItemRepository extends JpaRepository<StorageItem, Long>{
    
    @Query("SELECT s FROM StorageItem s WHERE s.userId = :uid")
    List<StorageItem> findAllByUserId(@Param("uid") Long uid);
}
