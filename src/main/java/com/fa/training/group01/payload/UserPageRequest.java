package com.fa.training.group01.payload;

import java.util.Optional;

import lombok.Data;

@Data
public class UserPageRequest {
	Integer pageIndex;
	Integer pageSize;
	String email;
	String sortBy;
}
