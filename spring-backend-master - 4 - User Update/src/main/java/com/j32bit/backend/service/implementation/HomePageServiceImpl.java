package com.j32bit.backend.service.implementation;

import com.j32bit.backend.dto.HomePageDTO;
import com.j32bit.backend.entity.HomePage;
import com.j32bit.backend.repository.HomePageRepository;
import com.j32bit.backend.service.HomePageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/*
* Ana Sayfa bilgileri bu serviste saklanıyor. Sistemde kaç kullanıcı, kaç atanmış form ve
* kaç tane tamamlanmış form varsa onu bu servis sayesinde günceliyoruz ve verilerini alıyoruz
*
* */
@Service
@RequiredArgsConstructor
@Log4j2
public class HomePageServiceImpl implements HomePageService {
    @Autowired
    HomePageRepository homePageRepository;

    //Bu fonksiyon sayesinde veritabanımızda bulunan ana sayfa verilerini alıyoruz
    @Override
    public ArrayList<HomePageDTO> findAll() {
        ArrayList<HomePage> homePages = homePageRepository.findAll();
        ArrayList<HomePageDTO> homePageDtos = new ArrayList<>();
        for(HomePage homePage : homePages) {
            HomePageDTO homePageDto = new HomePageDTO();
            BeanUtils.copyProperties(homePage,homePageDto);

            homePageDtos.add(homePageDto);
        }
        log.info("Home page values returned: {}", homePageDtos);
        return homePageDtos;
    }
}

