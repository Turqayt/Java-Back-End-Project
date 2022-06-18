package com.j32bit.backend.service;

import com.j32bit.backend.dto.ApplicationPageDTO;
import com.j32bit.backend.dto.PageFormDTO;
import com.j32bit.backend.dto.UserApplicationDTO;
import com.j32bit.backend.dto.application.ApplicationCreateDTO;
import com.j32bit.backend.dto.application.ApplicationDTO;
import com.j32bit.backend.dto.form.FormCreateDTO;
import com.j32bit.backend.dto.form.FormDTO;
import com.j32bit.backend.dto.page.PageDTO;
import com.j32bit.backend.dto.version.VersionDTO;

import java.util.ArrayList;

public interface FormManagementService {
    UserApplicationDTO creatUserApplication(UserApplicationDTO applicationDTO);
    ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO);
    ApplicationDTO applicationList(Integer id);
    ApplicationDTO updateApplication(Integer id, ApplicationCreateDTO applicationUpdateDTO);
    ArrayList<ApplicationDTO> findAll();

    VersionDTO createVersion(String version, int applicationId);
    //#ApplicationPage
    ApplicationPageDTO createApplicationPage(ApplicationPageDTO applicationPageDTO);

    //#Page
    PageDTO createPage(PageDTO pageDTO);
    //#Form
    FormDTO createForm(FormCreateDTO formCreateDTO);
    //#PageForm
    PageFormDTO createPageForm(PageFormDTO pageFormDTO);



}
