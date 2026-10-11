
package com.campusconnect.security;

import com.campusconnect.repo.AdminRepo;
import com.campusconnect.repo.FacultyRepo;
import com.campusconnect.repo.StudentRepo;
import com.campusconnect.service.CampusUserDetailsService;

/*
 * Compatibility class only.
 * Do not add @Service or @Component here.
 * CampusUserDetailsService is the active Spring bean.
 */
@Deprecated
public class CustomUserDetailsService extends CampusUserDetailsService {

    public CustomUserDetailsService(
            StudentRepo studentRepo,
            FacultyRepo facultyRepo,
            AdminRepo adminRepo) {

        super(studentRepo, facultyRepo, adminRepo);
    }
}
