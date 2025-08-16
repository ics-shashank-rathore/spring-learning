package com.learning.spring.common.response;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

public class ApiResponseMapper {

	public static <T> ApiResponse<T> toApiResponse(T data) {
		return ApiResponse.<T>builder().result(data).build();
	}

	public static <T> ApiResponse<T> toApiResponse(Page<T> page) {
		return ApiResponse.<T>builder().summary(new Summary(page.getTotalElements(), 0, 0, 0)).results(page.getContent()).build();
	}

	public static <T, D> ApiResponse<D> toApiResponse(Page<T> page, Function<T, D> mapper) {
		List<D> dtoList = page.getContent().stream().map(mapper).toList();
		return ApiResponse.<D>builder().summary(new Summary(page.getTotalElements(), 0, 0, 0)).results(dtoList).build();
	}
}

