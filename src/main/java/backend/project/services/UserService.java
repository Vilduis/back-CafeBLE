package backend.project.services;

import backend.project.dtos.DTOUser;
import backend.project.entities.User;

import java.util.List;

public interface UserService {
    public User findByUsername(String username);
    public User findById(Long id);
    public User insertUser(DTOUser dtoUser);
    public User updateUser(DTOUser dtoUser);
    public List<User> listAllUser();
    public void deleteUser(Long id);
}
