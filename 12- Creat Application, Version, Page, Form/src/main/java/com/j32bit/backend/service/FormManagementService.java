package com.j32bit.backend.service;

import com.j32bit.backend.dto.*;

import java.util.ArrayList;

public interface FormManagementService {
    ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO);
    ApplicationDTO applicationList(Integer id);
    ArrayList<ApplicationDTO> findAll();

    UserApplicationDTO creatUserApplication(UserApplicationDTO applicationDTO);
    VersionDTO createVersion(String version, int applicationId);

    ApplicationPageDTO createApplicationPage(ApplicationPageDTO applicationPageDTO);


}
