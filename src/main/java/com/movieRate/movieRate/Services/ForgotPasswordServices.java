package com.movieRate.movieRate.Services;

import org.springframework.ui.Model;

public interface ForgotPasswordServices {
    boolean createToken(String email, Model model);
    boolean validateResetToken(String token,Model model);
    boolean resetUserPassword(String email,String password,Model model);
}
