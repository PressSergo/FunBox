package com.example.demo.controller;

import com.example.demo.domain.model.Link;
import com.example.demo.domain.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController(value = "/")
public class LinkCrudController {

    @Autowired
    LinkRepository linkRepository;

    @Qualifier("redisTemplate")
    @Autowired
    RedisTemplate resdisTemplate;

    @RequestMapping(method = RequestMethod.POST,value = "/visited_links",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus visitedLink(@RequestBody List<Link> links){
        linkRepository.saveAll(links);
        return HttpStatus.OK;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/visited_domains",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Link>> visitedDomains(@RequestParam("from") Long from , @RequestParam("to") Long to){
        return ResponseEntity.ok(
                ((List<Link>) linkRepository.findAll())
                        .stream()
                        .filter(p-> from < p.getDate())
                        .filter(p-> to > p.getDate())
                        .collect(Collectors.toList()));
    }
}
