package com.movieRate.movieRate.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super("Sorry , we couldn't find the page that you were looking for.");
    }
   
    public String notFoundTitle(){

       return  "Sorry , we couldn't find the movie you were looking for.";
    }
    public String notFoundUser(){
        return "Sorry , we couldn't find the user that you were looking for.";
    }

}
