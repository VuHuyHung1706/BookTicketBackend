package com.web.backend.service.account;

import com.web.backend.dto.request.*;
import com.web.backend.dto.response.*;

public interface AccountService {
//    CustomerResponse registerUser(UserRegistrationRequest request);

    void sendOtp(UserRegistrationRequest request);
    CustomerResponse completeRegistration(UserRegistrationRequest request, String otp);
    void changePassword(ChangePasswordRequest request);
    CustomerResponse updateProfile(UpdateProfileRequest request);
    CustomerResponse getMyProfile();
    ManagerResponse getManager();
}
