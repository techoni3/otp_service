package com.resync.auth.services;

class LoginServiceImplTest {

  void register() {
    /*
    Test Cases:
     - returns null when otp is successfully sent
     - if request is made multiple times within ttl, same otp is present in redis
     - user is present with necessary fields in database when otp is successfully sent
     - throws error when phone number is invalid
     - throws error when user creation fails
     - throws error when set otp fails // redis is down
     - throws error when sendOtp fails
     */
    return;
  }

  void authenticateOtp() {

    /*
    Test Cases:
    - returns null when otp authentication is successful
    - user record is updated when authentication is successful
    - throws an error if user is not present
    - throws an error if invalid OTP is sent in request
    - throws an error if OTP is not present in redis // expiry time has reached
    - check if otp in redis store is null after authentication is successful
    */
    return;
  }
}
