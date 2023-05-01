package com.einformes.iproyect.services;

import com.einformes.iproyect.controller.dtos.requests.User.CreateUserRequest;
import com.einformes.iproyect.controller.dtos.requests.User.UpdateUserRequest;
import com.einformes.iproyect.controller.dtos.responses.User.GetUserResponse;
import com.einformes.iproyect.entities.User;
import com.einformes.iproyect.repositories.IUserRepository;
import com.einformes.iproyect.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public GetUserResponse getUser(Long id) {
        return from(id);
    }

    @Override
    public void create(CreateUserRequest request) {
        User user = from(request);
        repository.save(user);
    }
    private User from(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    @Override
    public List<GetUserResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public GetUserResponse update(UpdateUserRequest request, Long id) {
        User user = findOneAndEnsureExist(id);
        user = update(user, request);
        return from(user);
    }

    private User update(User user, UpdateUserRequest request) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return repository.save(user);
    }

    private User findOneAndEnsureExist(Long id){
        return repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
    }



    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setUsername(user.getUsername());
        response.setIduser(user.getIduser());
        response.setEmail(user.getEmail());
        return response;
    }

    private GetUserResponse from(Long idUser){
        return repository.findById(idUser)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
    }

}
