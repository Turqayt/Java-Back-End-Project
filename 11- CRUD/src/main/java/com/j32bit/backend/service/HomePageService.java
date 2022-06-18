package com.j32bit.backend.service;

import com.j32bit.backend.dto.homePage.HomePageDTO;


import java.util.ArrayList;

public interface HomePageService {
    ArrayList<HomePageDTO> findAll();
    HomePageDTO updateHomePage(HomePageDTO homePageDTO);
}
