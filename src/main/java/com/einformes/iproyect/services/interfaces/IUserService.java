package com.einformes.iproyect.services.interfaces;

import com.einformes.iproyect.controller.dtos.requests.CreateUserRequest;
import com.einformes.iproyect.controller.dtos.requests.UpdateUserRequest;
import com.einformes.iproyect.controller.dtos.responses.GetUserResponse;

import java.util.List;

public interface IUserService {

    GetUserResponse getUser(Long id);

    void create(CreateUserRequest request);

    List<GetUserResponse> list();

    void delete(Long id);

    GetUserResponse update(UpdateUserRequest request, Long id);
}
