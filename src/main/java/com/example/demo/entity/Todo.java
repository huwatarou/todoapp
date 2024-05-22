package com.example.demo.entity;

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
	private String title;
	private String description;
	private Boolean status;
	
	// コンストラクタ、ゲッター、セッターはLombokによって自動生成されます。
}
