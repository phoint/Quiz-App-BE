	package com.fa.training.group01.controller.student;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.dto.RegisterFormUserDTO;
import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.payload.ResetPasswordRequest;
import com.fa.training.group01.service.IEmailService;
import com.fa.training.group01.service.IRoleService;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.RequestURL;
import com.fa.training.group01.util.ResponseMessage;
import com.fa.training.group01.util.StringHelper;
import com.fa.training.group01.util.UrlConstant;
import com.fa.training.group01.validator.RegisterUserFormValidator;
import com.fa.training.group01.validator.ResetPasswordRequestValidator;

@RestController
public class HomeController {
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	/* Validators */
	@Autowired
	private RegisterUserFormValidator registerUserFormValidator;
	@Autowired
	private ResetPasswordRequestValidator resetPasswordRequestValidator;
	/* Other services */
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IEmailService emailService;

	@InitBinder()
	public void initBinder(WebDataBinder binder) {
		if (StringHelper.lowerFirstLetter(RegisterFormUserDTO.class.getSimpleName()).equals(binder.getObjectName()))
			binder.setValidator(registerUserFormValidator);
		if (StringHelper.lowerFirstLetter(ResetPasswordRequest.class.getSimpleName()).equals(binder.getObjectName()))
			binder.setValidator(resetPasswordRequestValidator);
	}

	@RequestMapping(value = RequestURL.Public.Path.REGISTER, method = RequestMethod.POST)
	public String register(@Valid @RequestBody RegisterFormUserDTO registerForm) {
		UserEntity userEntity = convertToEntity(registerForm);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setRole(roleService.findByName(Role.STUDENT));
		userEntity.setActive(true);
		if (userService.insert(userEntity)) {
			return "success";
		}
		return "failed";
	}

	@RequestMapping(value = RequestURL.Public.Path.FORGOT_PASSWORD, method = RequestMethod.GET)
	public String fotgotPassword(@NotEmpty @Email @RequestParam(name = "email", required = true) String email) {
		UserEntity userEntity;
		if ((userEntity = userService.findOneByEmailAndActive(email, true)) == null) {
			return ResponseMessage.ForgotPassword.NOT_FOUND_ACCOUNT;
		}
		String token;
		while (userService.existsByKeyToken(token = StringHelper.generatePassword(50)))
			;
		userEntity.setKeyToken(token);
		userService.update(userEntity);
		Map<String, Object> model = new HashMap<>();
		model.put("url", UrlConstant.CLIENT_PREFIX_SERVER + "/" + UrlConstant.Client.RESETPASSWORD);
		model.put("token", token);
		emailService.sendResetPasswordMail(model, email);
		return ResponseMessage.ForgotPassword.SUCCESS;
	}

	@RequestMapping(value = RequestURL.Public.Path.RESET_PASSWORD, method = RequestMethod.POST)
	public String resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
		UserEntity userEntity = userService.findOneByKeyToken(resetPasswordRequest.getToken());
		userEntity.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
		userEntity.setKeyToken(null);
		if (!userService.update(userEntity)) {
			return ResponseMessage.ResetPassword.FAILED.toString();
		}
		return ResponseMessage.ResetPassword.SUCCESS.toString();
	}

	@RequestMapping(value = RequestURL.Public.Path.EXISTS_RESET_PASSWORD_TOKEN, method = RequestMethod.GET)
	public String existsResetPasswordToken(@RequestParam(name = "token") String token)
			throws UnsupportedEncodingException {
		return "" + userService.existsByKeyToken(URLDecoder.decode(token, StandardCharsets.UTF_8.name()));
	}

	private UserEntity convertToEntity(RegisterFormUserDTO registerForm) {
		return modelMapper.map(registerForm, UserEntity.class);
	}

}
