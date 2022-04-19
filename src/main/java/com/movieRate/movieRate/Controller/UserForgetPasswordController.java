package com.movieRate.movieRate.Controller;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.RestPasswordToken;
import com.movieRate.movieRate.Repository.AppUserRepo;
import com.movieRate.movieRate.Repository.RestPasswordTokenRepo;
import com.movieRate.movieRate.Services.ForgotPasswordServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Properties;
@Controller
public class UserForgetPasswordController {
    @Autowired
    private AppUserRepo userRepository;

    @Autowired
    private RestPasswordTokenRepo confirmationTokenRepository;

    @Autowired
    ForgotPasswordServicesImp forgotPasswordServicesImp;






    // https://stackabuse.com/password-encoding-with-spring-security/
    // to encode our password

    
    /**
     * Display the forgot password page and form
     */
    @RequestMapping(value="/forgot-password", method= RequestMethod.GET)
    public String displayResetPassword() {

        return "forgotEmail";
    }

    /**
     * Receive email of the user, create token and send it via email to the user
     */
    @RequestMapping(value="/forgot-password", method=RequestMethod.POST)
    public String createToken(Model model, @RequestParam String email) {
      if (forgotPasswordServicesImp.createToken(email,model))return "redirect:/forgot-password?secs";

        return "redirect:/forgot-password?error";
    }







    @RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
    public String validateResetToken(Model model, @RequestParam("token")String confirmationToken){
        if (forgotPasswordServicesImp.validateResetToken(confirmationToken,model))return "changePassword";
        return "redirect:/login?invalidToken";
    }





    /**
     * Receive the token from the link sent via email and display form to reset password
     */
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetUserPassword( @RequestParam String token, @RequestParam String password,Model model) {


     if (forgotPasswordServicesImp.resetUserPassword(token,password,model))return "redirect:/login?secs";

        return "redirect:/login?invalidToken";
    }

}
