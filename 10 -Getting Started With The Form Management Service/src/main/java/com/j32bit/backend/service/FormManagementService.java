package com.j32bit.backend.service;

import com.j32bit.backend.dto.ApplicationCreateDTO;
import com.j32bit.backend.dto.ApplicationDTO;
import com.j32bit.backend.dto.VersionDTO;

import java.util.ArrayList;

public interface FormManagementService {
    ApplicationDTO createApplication(ApplicationCreateDTO applicationCreateDTO);
    ArrayList<ApplicationDTO> findAll();
    public VersionDTO createVersion(String version, int applicationId);
}
