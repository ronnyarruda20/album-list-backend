package com.list.music.pagination;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public abstract class Pageable<T> {

    private PageInfo pageInfo;

    Pageable() {
        
    }
    
    Pageable(Page<T> page) {
        this.pageInfo = new PageInfo(page);
    }
}