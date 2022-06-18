package com.j32bit.backend.service.implementation;

import com.j32bit.backend.dto.ApplicationPageDTO;
import com.j32bit.backend.dto.PageFormDTO;
import com.j32bit.backend.dto.UserApplicationDTO;
import com.j32bit.backend.dto.application.ApplicationCreateDTO;
import com.j32bit.backend.dto.application.ApplicationDTO;
import com.j32bit.backend.dto.form.FormCreateDTO;
import com.j32bit.backend.dto.form.FormDTO;
import com.j32bit.backend.dto.page.PageDTO;
import com.j32bit.backend.dto.version.VersionCreateDTO;
import com.j32bit.backend.dto.version.VersionDTO;
import com.j32bit.backend.entity.*;
import com.j32bit.backend.exception.NotFoundException;
import com.j32bit.backend.repository.*;
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

import java.sql.Timestamp;
import java.time.Instant;
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
    @Autowired
    PageRepository pageRepository;
    @Autowired
    FormRepository formRepository;
    @Autowired
    PageFormRepository pageFormRepository;
    private final UserService userService;
    private int versionId, pageId, formId;
    @Override
    public UserApplicationDTO creatUserApplication(UserApplicationDTO userApplicationDTO) {
        final UserApplication userApplication = userApplicationRepository.save(new UserApplication(userApplicationDTO.getApplicationId(),userApplicationDTO.getUserId(),
                userApplicationDTO.getUpdateAt(),userApplicationDTO.getUpdateBy()));
        log.info("User_Application Created");
        return UserApplicationDTO.of(userApplication);
    }
    @Override
    public ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO) {
        final Application application = applicationRepository.save(new Application(applicationCreateDTO.getDescription(),
                applicationCreateDTO.getName(),"App164800043612084",null,null,"1.0", applicationCreateDTO.getFormtype()));
        log.info("Application Created");

        //#Version
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

        //#Page
        PageDTO pageDTO = new PageDTO();
        pageDTO.setTitle("SCREEN1");
        pageDTO.setPageNameHide(false);
        pageDTO.setPageNumber(1);
        createPage(pageDTO);

        //#Application_Page
        ApplicationPageDTO applicationPageDTO = new ApplicationPageDTO();
        applicationPageDTO.setUpdateAt(null);
        applicationPageDTO.setUpdateBy("null");
        applicationPageDTO.setVersion("1.0");
        applicationPageDTO.setApplicationId(application.getId());
        applicationPageDTO.setPageId(1);
        applicationPageDTO.setVersionId(versionId);
        applicationPageDTO.setTemplateUrl("url");
        applicationPageDTO.setPageId(pageId);
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
    //Update Application
    @Override
    public ApplicationDTO updateApplication(Integer id, ApplicationCreateDTO applicationCreateDTO) {
        final Application application = applicationRepository.findById(id).orElseThrow(() -> new NotFoundException("Application Not Found"));
        log.info("Application Found");
        String updateBy = SecurityContextHolder.getContext().getAuthentication().getName();
        application.setName(applicationCreateDTO.getName());
        application.setDescription(applicationCreateDTO.getDescription());
        application.setFormType(applicationCreateDTO.getFormtype());
        application.setUpdatedAt(Timestamp.from(Instant.now()));
        application.setUpdatedBy(updateBy);
        log.info("Application Values Assigned");
        final Application updateApplication = applicationRepository.save(application);
        log.info("Application Data Saved in Database");
        return ApplicationDTO.of(updateApplication);
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
    //#Page
    @Override
    public PageDTO createPage(PageDTO pageDTO) {
        final Page page = pageRepository.save(new Page(pageDTO.getTitle(),pageDTO.isPageNameHide(),pageDTO.getPageNumber()));
        pageId = page.getId();

        //#Form
        FormCreateDTO formCreateDTO = new FormCreateDTO();
        formCreateDTO.setTitle("");
        createForm(formCreateDTO);

        //#Page_Form
        PageFormDTO pageFormDTO = new PageFormDTO();
        pageFormDTO.setFormId(formId);
        pageFormDTO.setPageId(pageId);
        pageFormDTO.setVersionId(versionId);
        createPageForm(pageFormDTO);

        log.info("Page Created");
        return PageDTO.of(page);
    }
    //#Form
    @Override
    public FormDTO createForm(FormCreateDTO formCreateDTO) {
        final Form form = formRepository.save(new Form(formCreateDTO.getTitle(),null,"null"));
        formId = form.getId();
        log.info("Form Created1");
        return FormDTO.of(form);
    }
    //#PageForm
    @Override
    public PageFormDTO createPageForm(PageFormDTO pageFormDTO) {
        final PageForm pageForm = pageFormRepository.save(new PageForm(null,"null",pageFormDTO.getFormId(),pageFormDTO.getPageId(),
                pageFormDTO.getVersionId()));
        log.info("Page_Form Created");
        return PageFormDTO.of(pageForm);
    }

}
