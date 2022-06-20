package com.j32bit.backend.service.implementation;

import com.j32bit.backend.dto.*;
import com.j32bit.backend.entity.*;
import com.j32bit.backend.exception.NotFoundException;
import com.j32bit.backend.repository.ApplicationPageRepository;
import com.j32bit.backend.repository.ApplicationRepository;
import com.j32bit.backend.repository.UserApplicationRepository;
import com.j32bit.backend.repository.VersionRepository;
import com.j32bit.backend.service.FormManagementService;
import com.j32bit.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Log4j2
public class FormManagementServiceImpl implements FormManagementService {
    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    VersionRepository versionRepository;

    @Autowired
    UserApplicationRepository userApplicationRepository;
    @Autowired
    ApplicationPageRepository applicationPageRepository;
    private final UserService userService;
    private int versionId;
    @Override
    public ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO) {
        final Application application = applicationRepository.save(new Application(applicationCreateDTO.getDescription(),
                applicationCreateDTO.getName(),"App164800043612084",null,null,"1.0", applicationCreateDTO.getFormtype()));
        log.info("Application Created");

        String createdByName = SecurityContextHolder.getContext().getAuthentication().getName();

        UserApplicationDTO userApplicationDTO = new UserApplicationDTO();
        userApplicationDTO.setApplicationId(application.getId());
        User user = userService.findByUsername(createdByName);
        userApplicationDTO.setUserId(user.getId());
        userApplicationDTO.setUpdateAt(null);
        userApplicationDTO.setUpdateBy("null");
        creatUserApplication(userApplicationDTO);
        log.info("User_Application Created2");

        createVersion("1.0",application.getId());
        log.info("Version Created2");

        ApplicationPageDTO applicationPageDTO = new ApplicationPageDTO();
        applicationPageDTO.setUpdateAt(null);
        applicationPageDTO.setUpdateBy("null");
        applicationPageDTO.setVersion("1.0");
        applicationPageDTO.setApplicationId(application.getId());
        applicationPageDTO.setPageId(1);
        applicationPageDTO.setVersionId(versionId);
        applicationPageDTO.setTemplateUrl("url");
        applicationPageDTO.setPageId(1);
        createApplicationPage(applicationPageDTO);
        log.info("Application_page Created2");

        return ApplicationDTO.of(application);
    }
    // application listi id olarakdöndürüyor
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ApplicationDTO applicationList(Integer id) {
        final Application application = applicationRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Not Found Exception"));

        return  ApplicationDTO.of(application);
    }


    @Override
    public ArrayList<ApplicationDTO> findAll() {
        ArrayList<Application> applications = applicationRepository.findAll();
        ArrayList<ApplicationDTO> applicationDTOS = new ArrayList<>();
        for(Application application : applications) {
            ApplicationDTO applicationDTO = new ApplicationDTO();
            BeanUtils.copyProperties(application,applicationDTO);

            applicationDTOS.add(applicationDTO);
        }
        return applicationDTOS;
    }

    @Override
    public UserApplicationDTO creatUserApplication(UserApplicationDTO userApplicationDTO) {
        final UserApplication userApplication = userApplicationRepository.save(new UserApplication(userApplicationDTO.getApplicationId(),userApplicationDTO.getUserId(),
                 userApplicationDTO.getUpdateAt(),userApplicationDTO.getUpdateBy()));
        log.info("User_Application Created");
        return UserApplicationDTO.of(userApplication);
    }
    //version oluşturuyor
    @Override
    public VersionDTO createVersion(String version, int applicationId){
        VersionCreateDTO versionCreateDTO = new VersionCreateDTO();
        versionCreateDTO.setVersion(version);
        versionCreateDTO.setApplicationId(applicationId);
        final Version version1 = versionRepository.save(new Version(versionCreateDTO.getVersion(),versionCreateDTO.getApplicationId()));
        versionId = version1.getId();
        log.info("Version Created");
        return VersionDTO.of(version1);
    }

    @Override
    public ApplicationPageDTO createApplicationPage(ApplicationPageDTO applicationPageDTO) {
        final ApplicationPage applicationPage = applicationPageRepository.save(new ApplicationPage(applicationPageDTO.getUpdateAt(),applicationPageDTO.getUpdateBy(),
                applicationPageDTO.getVersion(),applicationPageDTO.getApplicationId(),applicationPageDTO.getPageId(),applicationPageDTO.getVersionId(),
                applicationPageDTO.getTemplateUrl()));
        log.info("Application_Page Created");
        return ApplicationPageDTO.of(applicationPage);
    }

}
