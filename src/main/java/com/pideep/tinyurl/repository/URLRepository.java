package com.pideep.tinyurl.repository;

import com.pideep.tinyurl.model.URLMappingDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URLMappingDB, Long> {
    Optional<URLMappingDB> findByShortURL(String shortURL);
    Optional<URLMappingDB> findByLongURL(String longURL);
    //boolean existsByLongURL(String longURL);
}
