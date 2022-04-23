package com.movieRate.movieRate.Services;

import com.movieRate.movieRate.ModuleWeb.AppUser;
import com.movieRate.movieRate.ModuleWeb.RestPasswordToken;
import com.movieRate.movieRate.Repository.AppUserRepo;
import com.movieRate.movieRate.Repository.RestPasswordTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Properties;

@Service
public class ForgotPasswordServicesImp implements ForgotPasswordServices {
    @Autowired
    private AppUserRepo userRepository;

    @Autowired
    private RestPasswordTokenRepo confirmationTokenRepository;
    @Autowired
    PasswordEncoder hashPassword;

    @Override
    public boolean createToken(String email, Model model) {
        AppUser existingUser = userRepository.findAppUserByEmail(email);
        RestPasswordToken restPasswordToken = null;
        if (existingUser != null) {
            // create token
            restPasswordToken = new RestPasswordToken(existingUser);

            // save it
            confirmationTokenRepository.save(restPasswordToken);

            // create the email
            final String username = "yammovies9@gmail.com";
            final String password = "Yam&1234";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("from@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(email)
                );
                message.setSubject("Testing Gmail TLS");
                message.setText("To complete the password reset process, please click here:\n "
                        + "https://movierate2.herokuapp.com/confirm-reset?token=" + restPasswordToken.getConfirmationToken());

                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            model.addAttribute("message", "Request to reset password received. Check your inbox for the reset link.");
            return true;
        } else {
            model.addAttribute("message", "This email does not exist!");
            return false;
        }

    }

    @Override
    public boolean validateResetToken(String token, Model model) {
        RestPasswordToken token1 = confirmationTokenRepository.findByConfirmationToken(token);
        if (token != null) {
            if (token1.getCreatedDate().before(Calendar.getInstance().getTime()) && token1.isValid()) {
                AppUser user = userRepository.findAppUserByEmail(token1.getUser().getEmail());
                userRepository.save(user);
                model.addAttribute("user", user);
                model.addAttribute("emailId", user.getEmail());
                token1.setValid(false);
                confirmationTokenRepository.save(token1);
                model.addAttribute("token", token);
                return true;
            } else {
                model.addAttribute("message", "The Token is expired");
                return false;
            }

        } else {
            model.addAttribute("message", "The link is invalid or broken!");
            return false;
        }
    }

    @Override
    public boolean resetUserPassword(String token, String password, Model model) {
        AppUser user = confirmationTokenRepository.findByConfirmationToken(token).getUser();
        if (user != null) {
            user.setPassword(hashPassword.encode(password));
            userRepository.save(user);
            model.addAttribute("sec", "Password successfully reset. You can now log in with the new credentials.");
            return true;
        } else {
            model.addAttribute("message", "The link is invalid or broken!");

            return false;
        }

    }
}
