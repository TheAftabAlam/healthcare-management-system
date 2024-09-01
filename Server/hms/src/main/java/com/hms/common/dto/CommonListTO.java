package com.hms.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CommonListTO<T> {

	private Long totalRow;

	private Integer pageCount;

	private List<T> dataList = new ArrayList<>();
}
