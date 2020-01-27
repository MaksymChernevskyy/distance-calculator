package com.imoovo.boundary;

import com.imoovo.business.entity.FindUserException;
import com.imoovo.business.entity.Role;
import com.imoovo.business.entity.User;
import com.imoovo.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Api(value = "/user/", description = "Current user", tags = {"CurrentUser"})
@RestController
@RequestMapping("/user")
public class UserResource {
    private UserService userService;
    private DistanceServiceExceptonHandler exceptonHandler = new DistanceServiceExceptonHandler();

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = User.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> add(@RequestBody(required = false) User user) {
        try {
            User addedUser = userService.saveUser(user);
            return getResponseEntity(addedUser);
        } catch (Exception e) {
            return exceptonHandler.getResponseForError("Internal server error");
        }
    }

    @GetMapping("/{userId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user.getRole() == Role.ADMIN) {
                return getResponseForSuccess(userService.getAllUsers());
            } else {
                return getResponseForNoAccess("You have no access");
            }
        } catch (FindUserException e) {
            return exceptonHandler.getResponseForNoContent(String.format("Cannot find user with id: %d", userId));
        } catch (Exception e) {
            return exceptonHandler.getResponseForError("Internal server error");
        }
    }

    private ResponseEntity<?> getResponseEntity(User user) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(URI.create(String.format("/user/%d", user.getId())));
        return new ResponseEntity<>(user, responseHeaders, HttpStatus.CREATED);
    }

    public ResponseEntity<String> getResponseForNoAccess(String message) {
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<List<User>> getResponseForSuccess(List<User> users) {
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
