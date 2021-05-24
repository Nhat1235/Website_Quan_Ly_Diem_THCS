package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.TaiKhoanGv;
import com.example.demo.model.TaiKhoanHs;
import com.example.demo.repositories.TaiKhoanGvRepository;
import com.example.demo.repositories.TaiKhoanHSRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    TaiKhoanGvRepository userRepository;

    @Autowired
    TaiKhoanHSRepository hsrepo;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       Optional<TaiKhoanGv> user = userRepository.findTaiKhoanGvByTentk(userName);
        System.out.println("tai khoan:" +user+", ten dang nhap:" + userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }
    
}
