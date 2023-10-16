package com.demojwt.core.controller;

import com.demojwt.core.model.User;
import com.demojwt.core.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list/all")
    @RolesAllowed("ROLE_ROLE")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class RoleToUserForm {
    private String userName;
    private String roleName;
}
