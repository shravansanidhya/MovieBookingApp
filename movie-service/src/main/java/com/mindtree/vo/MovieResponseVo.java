package com.mindtree.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseVo {

	private long movieId;

//	String nameRegEx = "^[0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2}$";
			
	@NotBlank
	private String movieTitle;

	@NotBlank
	@Max(value = 5, message = "*Maximum rating can be 5")
	private double rating;

	@Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)")
//	@Value("#{response.releaseDate matches response.nameRegEx}")
	private String releaseDate;

	@NotNull
	private String directorName;

	@NotNull
	private String producer;

	@NotNull
	@Min(value = 7, message = "*Minimum 7 characters sholud be present")
	private String genre;

	@NotNull
	private String language;
	
	@NotNull
	private String movieImageURL;

	
	
	
	
}
