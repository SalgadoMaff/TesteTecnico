package com.example.testetecnico.services;

import com.example.testetecnico.entities.Account;
import com.example.testetecnico.entities.Profile;
import com.example.testetecnico.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public boolean canAddProfile(Account account){
        return account.getProfiles().size()<4;
    }

    public boolean noProfiles(Account account){
        return account.getProfiles().isEmpty();
    }



    public Profile addProfile(Account account, Profile profile){
        if (canAddProfile(account)){
            if(noProfiles((account))){
                profile.setAccount(account);
                return profileRepository.save(profile);
            }else{
                for (int i = 0; i < account.getProfiles().size(); i++) {
                    if(account.getProfiles().get(i).getName().equals(profile.getName())){
                        break;
                    }else if(i==account.getProfiles().size()-1){
                        profile.setAccount(account);
                        return profileRepository.save(profile);
                    }
                }
            }
        }
        return null;
    }

    public Optional<Profile> listProfilesByAccount(Account account){
        return profileRepository.findByAccount(account);
    }



}
