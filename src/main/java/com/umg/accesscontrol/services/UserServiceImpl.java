package com.umg.accesscontrol.services;

import com.umg.accesscontrol.models.Access;
import com.umg.accesscontrol.models.AccessType;
import com.umg.accesscontrol.models.User;
import com.umg.accesscontrol.repositories.AccessRepository;
import com.umg.accesscontrol.repositories.AccessTypeRepository;
import com.umg.accesscontrol.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccessRepository accessRepository;
    private final AccessTypeRepository accessTypeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, AccessRepository accessRepository, AccessTypeRepository accessTypeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
        this.accessTypeRepository = accessTypeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User createUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {

        Optional<User> userSaved = userRepository.findById(userId);

        if (!userSaved.isPresent()) {
            throw new RuntimeException("El usuario no existe");
        }

        User updatedUser = userSaved.get();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        BeanUtils.copyProperties(user, updatedUser);

        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> userSaved = userRepository.findById(userId);

        if (!userSaved.isPresent()) {
            throw new RuntimeException("El cliente no existe");
        }

        userRepository.delete(userSaved.get());
    }

    public boolean userExists(User user) {
        Optional<User> userSaved = userRepository.findById(user.getId());
        return userSaved.isPresent();
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> userSaved = userRepository.findById(userId);
        if (!userSaved.isPresent()) {
            throw new RuntimeException("El usuario no existe");
        }

        return userSaved.get();
    }


    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getCustomerByUsername(s);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(s);
        }

        Optional<Access> access = accessRepository.findFirstByUserOrderByCreatedAtDesc(user.get());

        if (access.isPresent()) {
            Optional<AccessType> accessType = (access.get().getAccessType().getId() == 1) ? accessTypeRepository.findById(2L) :
                    accessTypeRepository.findById(1L);
            Access newAccess = new Access();
            newAccess.setUser(user.get());
            newAccess.setCreatedAt(LocalDateTime.now());
            newAccess.setAccessType(accessType.get());

            accessRepository.save(newAccess);
        } else {
            Optional<AccessType> accessType = accessTypeRepository.findById(1L);
            Access newAccess = new Access();
            newAccess.setUser(user.get());
            newAccess.setCreatedAt(LocalDateTime.now());
            newAccess.setAccessType(accessType.get());
            accessRepository.save(newAccess);
        }

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(), new ArrayList<>());
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.getCustomerByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return user.get();
    }
}
