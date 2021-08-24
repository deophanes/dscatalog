package com.dslearn.dscatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	private String password;

}
