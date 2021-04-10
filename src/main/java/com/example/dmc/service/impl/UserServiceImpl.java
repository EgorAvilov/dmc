package com.example.dmc.service.impl;

import com.example.dmc.entity.User;
import com.example.dmc.repository.UserRepository;
import com.example.dmc.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {//здесь сразу и помещать в очередь!!
    private final UserRepository userRepository;
    @Autowired
    AmqpTemplate template;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        String currentPrincipalName = authentication.getName();
        return findByUsername(currentPrincipalName);
    }
}
