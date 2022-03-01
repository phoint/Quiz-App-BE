package com.fa.training.group01.domain_model;

import com.fa.training.group01.entity.RoleEntity;

public enum Role {
	ADMIN(RoleName.ADMIN), STUDENT(RoleName.STUDENT);

	public final String name;

	private Role(String name) {
		this.name = "ROLE_" + name;
	}

	public static class RoleName {
		public static final String STUDENT = "STUDENT";
		public static final String ADMIN = "ADMIN";
	}

	public static boolean isRoleExists(String roleName) {
		if (roleName == null)
			return false;
		for (Role role : values()) {
			if (role.name.equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public static Role getRole(String roleName) {
		if (roleName == null)
			return null;
		for (Role role : values()) {
			if (role.name.equals(roleName)) {
				return role;
			}
		}
		return null;
	}

	public static Role valueOf(RoleEntity entity) {
		if (entity == null)
			return null;
		for (Role role : values()) {
			if (entity.getName().equals(role.name)) {
				return role;
			}
		}
		return null;
	}

}
