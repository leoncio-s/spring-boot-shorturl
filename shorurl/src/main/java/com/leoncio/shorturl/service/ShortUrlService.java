package com.leoncio.shorturl.service;

import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leoncio.shorturl.dao.ShortUrlDAO;
import com.leoncio.shorturl.dto.ShortUrlDto;
import com.leoncio.shorturl.dto.UrlDto;
import com.leoncio.shorturl.handler.CodeUrlInvalidoException;
import com.leoncio.shorturl.handler.ShortUrlNotFoundException;
import com.leoncio.shorturl.model.ShortUrl;
import com.leoncio.shorturl.repository.ShortUrlRepository;
import com.leoncio.shorturl.util.CodeGenerate;
import com.leoncio.shorturl.util.ValidaUrl;

@Service
public class ShortUrlService implements ShortUrlDAO{

    @Autowired
    private ShortUrlRepository repository;

    @Autowired
    private CodeGenerate codeGenerate;


    @Autowired
    private ValidaUrl validaUrl;

    @Override
    public ShortUrlDto get(String codeUrl) throws ShortUrlNotFoundException {

        if(codeUrl.isEmpty() || codeUrl.isBlank() || codeUrl.length() <= 3 || codeUrl.length() > 10){
            throw new CodeUrlInvalidoException();
        }
        
        ShortUrl shortUrl = repository.findByCodeUrl(codeUrl);

        if(shortUrl == null){
            throw new ShortUrlNotFoundException();
        }else{
            ShortUrlDto shortUrlDto = new ShortUrlDto();

            shortUrlDto.setShortUrl(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + shortUrl.getCodeUrl());
            shortUrlDto.setUrl(shortUrl.getUrl());
            return shortUrlDto;
        }
    }

    @Override
    @Transactional
    public ShortUrlDto save(UrlDto url) throws Exception {
        String formataUrl = validaUrl.validaUrl(url.getUrl());

        URL uri = new URL(formataUrl);

        String codUrl = null;

        ShortUrlDto shortUrlReturn = new ShortUrlDto();
        shortUrlReturn.setUrl(url.getUrl());

        if(repository.existsByUrl(uri.toString())){
            List<ShortUrl> shortUrl = repository.findByUrl(uri.toString());
            codUrl = shortUrl.get(0).getCodeUrl();
            shortUrlReturn.setShortUrl(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + codUrl);
        }else{
            do{
                codUrl = codeGenerate.code;
            }while(repository.existsByCodeUrl(codUrl));
            ShortUrl shortUrl = new ShortUrl();

            shortUrl.setCodeUrl(codUrl);
            shortUrl.setUrl(uri.toString());
            
            shortUrl = repository.save(shortUrl);

            shortUrlReturn.setShortUrl(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + shortUrl.getCodeUrl());
        }

        return shortUrlReturn;
    }

    @Override
    public ShortUrlDto update(ShortUrlDto shortUrl) throws Exception {
        return null;
    }

    @Override
    public boolean delete(ShortUrlDto shortUrl) throws ShortUrlNotFoundException {
        return false;
    }

}
