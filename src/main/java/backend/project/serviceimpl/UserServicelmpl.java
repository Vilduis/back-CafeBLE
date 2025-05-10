package backend.project.serviceimpl;

import backend.project.dtos.DTOUser;
import backend.project.entities.Authority;
import backend.project.entities.User;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.UserRepository;
import backend.project.security.SecurityUser;
import backend.project.services.AuthorityService;
import backend.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicelmpl implements UserService, UserDetailsService {
    @Autowired
    AuthorityService authorityService;

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username)
    {
        User userFound = userRepository.findByUsername(username);
        if(userFound==null)
        {
            throw new ResourceNotFoundException("User with username: "+ username+ " can not be found");
        }
        return userFound;
    }

    @Override
    public User findById(Long id)
    {
        User userFound= userRepository.findById(id).orElse(null);
        if (userFound==null)
        {
            throw  new ResourceNotFoundException("User with id:"+id+"can not be found");
        }
        return userFound;
    }

    public List<Authority> authorityListFromString(String authorityString) {

        List<String> authorityListString =  List.of(authorityString.split(";"));

        List<Authority> authorityList = new ArrayList<>();

        for (String authorityStringItem: authorityListString){
            Authority authority = authorityService.findByName(authorityStringItem);
            authorityList.add(authority);
        }

        return  authorityList;
    }

    @Override
    public User insertUser(DTOUser dtoUser)
    {
        User usernew = new User();
        usernew.setUsername(dtoUser.getUsername());
        usernew.setEmail(dtoUser.getEmail());
        usernew.setPassword(new BCryptPasswordEncoder().encode(dtoUser.getPassword()));
        usernew.setActive(true);
        usernew.setAuthorities(authorityListFromString(dtoUser.getAuthorities()));

        return userRepository.save(usernew);
    }
    @Override
    public User updateUser(DTOUser dtoUser)
    {
        User userFound = findById(dtoUser.getId());
        userFound.setUsername(dtoUser.getUsername());
        userFound.setEmail(dtoUser.getEmail());
        userFound.setPassword(new BCryptPasswordEncoder().encode(dtoUser.getPassword()));
        userFound.setActive(true);
        return userRepository.save(userFound);
    }

    @Override
    public List<User> listAllUser()
    {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id)
    {
        User userFound = userRepository.findById(id).orElse(null);
        if(userFound==null)
        {
            throw new ResourceNotFoundException("User with id:"+id+"can not be found");
        }
        userRepository.delete(userFound);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecurityUser(findByUsername(username));
    }

}

