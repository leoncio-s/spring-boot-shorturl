package com.leoncio.shorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoncio.shorturl.model.ShortUrl;

import java.util.List;


public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findById(long id);

    ShortUrl findByCodeUrl(String codeUrl);

    List<ShortUrl> findByUrl(String url);

    boolean existsByUrl(String url);

    boolean existsByCodeUrl(String code); 
}
