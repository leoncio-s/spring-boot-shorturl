package com.leoncio.shorturl.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoncio.shorturl.dto.ShortUrlDto;
import com.leoncio.shorturl.dto.UrlDto;
import com.leoncio.shorturl.handler.CodeUrlInvalidoException;
import com.leoncio.shorturl.handler.ShortUrlNotFoundException;
import com.leoncio.shorturl.service.ShortUrlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@Api(value = "ShortUrl Api", description = "Esta api tem como finalidade gerar um encurtador de URL, em que o usuário informará a URl que quer encurtar e o sistema ira retornar um url encuratada para o usuário, e ao acessar esta url encurtada o usuário será redirecionado para a url principal")
public class ShortUrlController {
    
    @Autowired
    private ShortUrlService service;

    @GetMapping("/{codeUrl}")
    @ApiOperation(value = "Recebe o código que foi salvo que está vinculado a url no banco, e a retonará")
    public ResponseEntity<ShortUrlDto> getUrl(@PathVariable("codeUrl") String codeUrl, HttpServletResponse response) throws Exception, ShortUrlNotFoundException{
        try{
            ShortUrlDto url = service.get(codeUrl);
            return ResponseEntity.ok(url);
        }catch(ShortUrlNotFoundException e){
            throw e;
        }catch(CodeUrlInvalidoException e){
            throw e;
        }catch(Exception e){
            throw e;
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Recebe o campo url, faz uma validação, salva a url no banco e o codigo vinculado e retorna o objeto com a url encurtada")
    public ResponseEntity<ShortUrlDto> shortUrl(@RequestBody UrlDto url) throws Exception{
        ShortUrlDto shortUrlDto = service.save(url);
        return ResponseEntity.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(shortUrlDto);
    }
}
