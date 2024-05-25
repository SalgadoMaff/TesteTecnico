package com.example.testetecnico.services;

import com.example.testetecnico.entities.Account;
import com.example.testetecnico.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByEmail(email);
        if(account.isPresent()){
            var obj = account.get();
            return User.builder().username(obj.getEmail())
                    .password(obj.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException(email);
        }
    }
}
