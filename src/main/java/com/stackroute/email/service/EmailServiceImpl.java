package com.stackroute.email.service;

import com.stackroute.email.domain.AlertMail;
import com.stackroute.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService{

    private EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository){
        this.emailRepository=emailRepository;
    }


    @Override
    public AlertMail save(AlertMail alertMail) {
        return emailRepository.save(alertMail);
    }

    }


