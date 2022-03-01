package com.fa.training.group01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO implements Serializable {
    private Object[] content;
    private Long TotalElements;
    private int TotalPage;

    public PageDTO(Page page) {
        this.content = page.getContent().toArray();
        TotalElements = page.getTotalElements();
        TotalPage = page.getTotalPages();
    }
}
