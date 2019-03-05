package com.redis.watcher.repositories;


import com.redis.watcher.domains.Numbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumbersRepository extends JpaRepository<Numbers, Long> {
    List<Numbers> findAll();
}
