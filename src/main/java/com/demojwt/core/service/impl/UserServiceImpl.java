package com.demojwt.core.service.impl;

import com.demojwt.core.model.User;
import com.demojwt.core.repository.RoleRepository;
import com.demojwt.core.repository.UserRepository;
import com.demojwt.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<User> getUsers() {
        log.info("Fetching all the users in the system");
        return userRepository.findAll();
    }
}
