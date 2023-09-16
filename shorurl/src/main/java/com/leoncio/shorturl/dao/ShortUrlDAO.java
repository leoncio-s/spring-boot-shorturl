package com.leoncio.shorturl.dao;

import com.leoncio.shorturl.dto.ShortUrlDto;
import com.leoncio.shorturl.dto.UrlDto;
import com.leoncio.shorturl.handler.ShortUrlNotFoundException;

public interface ShortUrlDAO {
    ShortUrlDto get(String codeUrl) throws ShortUrlNotFoundException;
    ShortUrlDto save(UrlDto url) throws Exception;
    ShortUrlDto update(ShortUrlDto shortUrl) throws Exception;
    boolean delete(ShortUrlDto shortUrl) throws ShortUrlNotFoundException;
}
