package com.fa.training.group01.controller.admin;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.entity.RoleEntity;
import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.payload.UpdateUserRequest;
import com.fa.training.group01.payload.UserPageRequest;
import com.fa.training.group01.service.IRoleService;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.specification.UserSpecification;
import com.fa.training.group01.util.RequestURL;
import com.fa.training.group01.util.ResponseMessage;
import com.fa.training.group01.util.StringHelper;
import com.fa.training.group01.validator.UpdateUserRequestValidator;

@RestController("AdminUserController")
@RequestMapping(RequestURL.Admin.ADMIN_PATH + RequestURL.Admin.User.USER_PATH)
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private UserSpecification userSpecification;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private UpdateUserRequestValidator updateUserRequestValidator;
	public static final int DEFAULT_PAGE_SIZE = 5;

	@InitBinder()
	public void initBinder(WebDataBinder binder) {
		if (StringHelper.lowerFirstLetter(UpdateUserRequest.class.getSimpleName()).equals(binder.getObjectName()))
			binder.setValidator(updateUserRequestValidator);
	}

	@RequestMapping(value = RequestURL.Admin.User.Path.ACCOUNT_LIST, method = RequestMethod.GET)
	public Page<UserEntity> listUsers(UserPageRequest userPageRequest) {
		int pageIndex = Optional.ofNullable(userPageRequest.getPageIndex()).orElse(1);
		int pageSize = Optional.ofNullable(userPageRequest.getPageSize()).orElse(DEFAULT_PAGE_SIZE);
		String email = Optional.ofNullable(userPageRequest.getEmail()).orElse("");
//		String sortBy = Optional.ofNullable(userPageRequest.getSortBy()).orElse("");
		Specification<UserEntity> specification = Specification.where(null);
		if (StringUtils.hasText(email)) {
			specification = specification.and(userSpecification.containsEmail(email));
		}
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
		return userService.findAll(specification, pageable);
	}

	@RequestMapping(value = RequestURL.Admin.User.Path.GET_USER, method = RequestMethod.GET)
	public UserEntity getUser(@PathVariable Integer id) {
		UserEntity userEntity = userService.findOneByID(id);
		return userEntity;
	}

	@RequestMapping(value = RequestURL.Admin.User.Path.UPDATE_USER, method = RequestMethod.POST)
	public String update(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
		UserEntity userEntity = userService.findOneByID(updateUserRequest.getId());
		userEntity.setActive(new Boolean(updateUserRequest.getActive()));
		if (StringUtils.hasText(updateUserRequest.getPassword())) {
			userEntity.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
		}
		RoleEntity roleEntity = roleService.findByName(Role.getRole(updateUserRequest.getRole()));
		if (roleEntity != null) {
			userEntity.setRole(roleEntity);
		}
		if (!userService.update(userEntity)) {
			return ResponseMessage.UpdateUser.FAILED;
		}
		return ResponseMessage.UpdateUser.SUCCESS;
	}
}
