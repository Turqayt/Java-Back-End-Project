package com.j32bit.backend.service.implementation;

import com.j32bit.backend.dto.ApplicationCreateDTO;
import com.j32bit.backend.dto.ApplicationDTO;
import com.j32bit.backend.dto.VersionCreateDTO;
import com.j32bit.backend.dto.VersionDTO;
import com.j32bit.backend.entity.Application;
import com.j32bit.backend.entity.Version;
import com.j32bit.backend.repository.ApplicationRepository;
import com.j32bit.backend.repository.VersionRepository;
import com.j32bit.backend.service.FormManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
@Log4j2
public class FormManagementServiceImpl implements FormManagementService {
    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    VersionRepository versionRepository;

    @Override
    public ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO) {
        final Application application = applicationRepository.save(new Application(applicationCreateDTO.getDescription(),
                applicationCreateDTO.getName(),"App164800043612084",null,null,"1.0", applicationCreateDTO.getFormtype()));
        log.info("Application Created");
        createVersion("1.0",application.getId());
        return ApplicationDTO.of(application);
    }

    public VersionDTO createVersion(String version, int applicationId){
        VersionCreateDTO versionCreateDTO = new VersionCreateDTO();
        versionCreateDTO.setVersion(version);
        versionCreateDTO.setApplicationId(applicationId);
        final Version version1 = versionRepository.save(new Version(versionCreateDTO.getVersion(),versionCreateDTO.getApplicationId()));
        return VersionDTO.of(version1);
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
}
