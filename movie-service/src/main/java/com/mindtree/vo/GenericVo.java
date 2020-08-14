package com.mindtree.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericVo {

	private String userMessage;

	private String statusCode;

	private String errorDescription;

	private String errorMessage;
}
