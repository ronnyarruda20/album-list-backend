package com.list.music.pagination;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class PageableResponse<T> extends Pageable<T> {

	@Transient
	private List<T> contents;

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public PageableResponse(Page<T> page) {
		super(page);
		this.contents = page.getContent();
	}


}
