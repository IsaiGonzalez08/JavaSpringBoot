package com.einformes.iproyect.controller;

import com.einformes.iproyect.controller.dtos.requests.User.CreateUserRequest;
import com.einformes.iproyect.controller.dtos.requests.User.UpdateUserRequest;
import com.einformes.iproyect.controller.dtos.responses.User.GetUserResponse;
import com.einformes.iproyect.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("{id}")
    public GetUserResponse get(@PathVariable Long id) {
        return service.getUser(id);
    }
    @GetMapping("list")
    public List<GetUserResponse> list() {
        return service.list();
    }

    @PostMapping
    public void create(@RequestBody CreateUserRequest request) {
        service.create(request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("{id}")
    public GetUserResponse update(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return service.update(request, id);
    }


}
