package com.fa.training.group01.domain_model;

import java.lang.annotation.*;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;


@SuppressWarnings("deprecation")
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}
