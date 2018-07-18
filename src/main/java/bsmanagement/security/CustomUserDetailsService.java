package bsmanagement.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bsmanagement.model.User;
import bsmanagement.model.jparepositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) 
            throws UsernameNotFoundException {
        // Let people login with email
        User user = userRepo.findById(usernameOrEmail).orElseThrow( () -> 
                new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
        return UserPrincipal.create(user);
    }
}
