package backend.project.services;

import backend.project.entities.Authority;


public interface AuthorityService {
    public Authority insertAuthority(Authority authority);
    public Authority findByName(String name);
}
