package com.stackroute.email.repository;

import com.stackroute.email.domain.AlertMail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends MongoRepository<AlertMail,String> {
    // List<AlertMail> save(AlertMail alertMail);

}
