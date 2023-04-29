package com.einformes.iproyect.services;

import com.einformes.iproyect.controller.dtos.requests.CreateUserRequest;
import com.einformes.iproyect.controller.dtos.requests.UpdateUserRequest;
import com.einformes.iproyect.controller.dtos.responses.GetUserResponse;
import com.einformes.iproyect.entities.User;
import com.einformes.iproyect.repositories.IUserRepository;
import com.einformes.iproyect.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public GetUserResponse getUser(Long id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            GetUserResponse from = this.from(user);
            return from;
        }
        throw new RuntimeException("No esta carnalito");
    }

    @Override
    public void create(CreateUserRequest request) {
        User user = from(request);
        repository.save(user);
    }

    @Override
    public List<GetUserResponse> list() {
        List<User> users = repository.findAll();
        List<GetUserResponse> userResponses = from(users);
        return userResponses;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public GetUserResponse update(UpdateUserRequest request, Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User updated = from(request, user);
            User saved = repository.save(updated);
            GetUserResponse response = from(saved);
            return response;
        }
        throw new RuntimeException("No esta carnal no se actualizó");
    }

    private User from(UpdateUserRequest request, User user) {
        user.setEmail(request.getEmail());
        return user;
    }

    private List<GetUserResponse> from(List<User> users) {
        List<GetUserResponse> userResponses = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            GetUserResponse response = from(user);
            userResponses.add(response);
        }
        return userResponses;
    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        return response;
    }
}
