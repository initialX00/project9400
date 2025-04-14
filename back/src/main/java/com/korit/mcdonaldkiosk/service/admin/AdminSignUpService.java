package com.korit.mcdonaldkiosk.service.admin;

import com.korit.mcdonaldkiosk.dto.request.ReqAdminSignUpDto;
import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.exception.DuplicatedValueException;
import com.korit.mcdonaldkiosk.exception.FieldError;
import com.korit.mcdonaldkiosk.repository.admin.AdminSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AdminSignUpService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AdminSignUpRepository adminSignUpRepository;


    public boolean duplicatedByAdminName(String adminName) {
        return adminSignUpRepository.findByAdminName(adminName).isPresent();
    }

    @Transactional(rollbackFor = Exception.class)
    public Admin signUp(ReqAdminSignUpDto reqAdminSignUpDto) {

        if(duplicatedByAdminName(reqAdminSignUpDto.getAdminName())) {
            throw new DuplicatedValueException(List.of(FieldError.builder()
                    .field("username")
                    .message("이미 존재하는 사용자이름입니다.")
                    .build()));
        }

        Admin admin = Admin.builder()
                .adminName(reqAdminSignUpDto.getAdminName())
                .adminPassword(passwordEncoder.encode(reqAdminSignUpDto.getAdminPassword()))
                .email(reqAdminSignUpDto.getEmail())
                .tradeName(reqAdminSignUpDto.getTradeName())
                .build();

                adminSignUpRepository.save(admin);
                return admin;
    }

}