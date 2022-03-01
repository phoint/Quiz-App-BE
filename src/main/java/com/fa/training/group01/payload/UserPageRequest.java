package com.fa.training.group01.payload;

import lombok.Data;

@Data
public class UserPageRequest {
	Integer pageIndex;
	Integer pageSize;
	String email;
	String sortBy;
}
