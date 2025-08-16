package com.learning.spring.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Summary 
{
    private long totalRecords;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
}