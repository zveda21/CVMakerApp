package org.example.service;

import org.example.manager.TokenManager;
import org.example.model.User;
import org.example.repository.UserRepositoryImpl;
import org.example.request.AuthRequest;
import org.example.response.AuthResponse;
import org.example.utils.GeneratePasswordWithHash;

public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl repository;
    private final TokenManager manager;

    public UserServiceImpl(UserRepositoryImpl repository, TokenManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    @Override
    public AuthResponse signup(AuthRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String username = request.getUsername();
        int userId = repository.signup(new User(username, GeneratePasswordWithHash.hashPassword(password), email));
        System.out.println("user--" + userId);
        String token = String.valueOf(manager.issueToken(userId));
        return new AuthResponse(userId, token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        User userResult = repository.login(username, password);
        String passwordInDatabase = userResult.getPassword();
        String usernameDatabase = userResult.getUsername();
        AuthResponse response = new AuthResponse();
        if (usernameDatabase == null) {
            System.out.println("Error");
        } else {
            if (GeneratePasswordWithHash.checkPassword(password, passwordInDatabase)) {
                int userId = userResult.getId();
                String token = manager.issueToken(userId);
                response.setUserId(userId);
                response.setToken(token);
            }
        }
        return response;
    }

    @Override
    public void addPersonalInformation(AuthRequest request, int userId) {
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String address = request.getAddress();
        String phone = request.getPhone();
        String workEmail = request.getWorkEmail();
        String position = request.getPosition();
        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setWorkMail(workEmail);
        user.setPosition(position);

        repository.updateUserInformation(user, userId);
    }

    @Override
    public AuthResponse getUserPersonalInformation(int userId) {
        AuthResponse response = new AuthResponse();
        User userInfo = repository.getById(userId);
        response.setUser(userInfo);
        return response;
    }

    @Override
    public int authorize(String token) {
        int result = manager.authorize(token);
        System.out.println("manager result---" + result);
        return result;
    }
}