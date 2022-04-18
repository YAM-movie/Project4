package com.movieRate.movieRate.ModuleWeb;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class RestPasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    private boolean isValid = true;

    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private AppUser appUser;

    public RestPasswordToken() {
    }

    public RestPasswordToken(AppUser user) {
        this.appUser = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser user) {
        this.appUser = user;
    }

    public long getTokenid() {
        return tokenId;
    }

    public void setTokenid(long tokenid) {
        this.tokenId = tokenid;
    }

}
