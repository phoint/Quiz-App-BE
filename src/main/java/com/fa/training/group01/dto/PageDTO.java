package com.fa.training.group01.dto;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object[] content;
	private Long TotalElements;
	private int TotalPage;

	public <T> PageDTO(Page<T> page) {
		this.content = page.getContent().toArray();
		TotalElements = page.getTotalElements();
		TotalPage = page.getTotalPages();
	}
}
