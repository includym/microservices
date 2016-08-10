package kr.mz.samples.msa.apigateway.util.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class RestPageImpl<T> extends PageImpl<T> {

	private static final long serialVersionUID = -6746924501976727621L;

	public RestPageImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public RestPageImpl(List<T> content) {
		super(content);
	}

	public RestPageImpl() {
		super(new ArrayList<T>());
	}

}