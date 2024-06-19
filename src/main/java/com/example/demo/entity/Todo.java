package com.example.demo.entity;


import org.springframework.data.annotation.Id;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotBlank(message = "タイトルは必須です")
	private String title;
	private String description;
	private Boolean status;
	@NotBlank(message = "日付け設定は必須です")
	private String time_limit;
	
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "login_id")
    private String userid;
}
	// コンストラクタ、ゲッター、セッターはLombokによって自動生成されます。
