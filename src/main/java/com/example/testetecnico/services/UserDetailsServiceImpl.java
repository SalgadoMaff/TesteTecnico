package com.example.testetecnico.services;

import com.example.testetecnico.entities.Account;
import com.example.testetecnico.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByEmail(username);
        ArrayList<String> aut = new ArrayList<>();
        aut.add("USER");
        if(account.isPresent()){
            var obj = account.get();
            return new CustomUserDetails(obj.getId(),
                    obj.getEmail(),
                    obj.getPassword(),
                    aut.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }

        throw new UsernameNotFoundException("User not found with email: " + username);

    }
}
