package com.list.music.pagination;

import javax.persistence.Transient;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageInfo {

    @Transient
    private long totalElements;
    
    @Transient
    private long totalPages;
    
    @Transient
    private long numberOfElements;
    
    @Transient
    private long pageNumber;
    
    @Transient
    private long pageSize;

    @SuppressWarnings("rawtypes")
    public PageInfo(Page page) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.numberOfElements = page.getNumberOfElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }
}