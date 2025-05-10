package backend.project.serviceimpl;


import backend.project.entities.Authority;
import backend.project.exceptions.InvalidDataException;
import backend.project.repositories.AuthorityRepository;
import backend.project.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Authority insertAuthority(Authority authority)
    {
        if(authority.getName()==null || authority.getName().isBlank())
        {
            throw new InvalidDataException("Authority name can not be blank");
        }
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findByName(String name)
    {
        Authority authorityFound= authorityRepository.findByName(name);
        if (authorityFound==null)
        {
            throw new InvalidDataException("Authority with name: "+ name+ " can not be found");
        }
        return authorityFound;
    }
}
