package com.example.demo.domain.repository;

import com.example.demo.domain.model.Link;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends KeyValueRepository<Link,String> {
}
