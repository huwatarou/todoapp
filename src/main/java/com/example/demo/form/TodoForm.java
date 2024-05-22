package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoForm {
	@NotBlank(message = "タイトルは必須です")
	private String title;
	
	private String description;
	
	private boolean status;
	@NotBlank(message = "日付け設定は必須です")
	private String time_limit;
}
