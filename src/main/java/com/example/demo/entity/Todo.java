package com.example.demo.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
	private Long id;
	@NotBlank(message = "タイトルは必須です")
	private String title;
	private String description;
	private Boolean status;
	@NotBlank(message = "日付け設定は必須です")
	private String time_limit;
	// コンストラクタ、ゲッター、セッターはLombokによって自動生成されます。
}
