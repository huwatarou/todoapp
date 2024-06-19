package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.entity.Todo;
import com.example.demo.form.TodoForm;
import com.example.demo.service.TodoService;

import jakarta.validation.Valid;
import java.util.Map;


@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	    
	@GetMapping(UrlConst.TODO)
	    public String getAllTodosForCurrentUser(Model model) {
	        List<Map<String, Object>> todos = todoService.findAllTodosForCurrentUser();
	        model.addAttribute("todos", todos);
	        return "todos";
	    }	
	
//	@GetMapping(UrlConst.TODO)
//	public String getAllTodos(Model model) {
//		List<Todo> todos = todoService.findAllTodos();
//		model.addAttribute("todos", todos);
//
//		return "todos";
//	}

	//追加
	@GetMapping("/create")
	public String showCreateTodoForm(Model model) {
		model.addAttribute("todoForm", new TodoForm());
		return "create-todo";
	}

	@PostMapping("/addTodo")
	public String submitForm(@Valid TodoForm todoForm, BindingResult result) {
		if (result.hasErrors()) {
			return "create-todo";
		}

		//フォームのデータを処理
		todoService.addTodo(todoForm.getTitle(), todoForm.getDescription(), todoForm.isStatus(),
				todoForm.getTime_limit());

		return "redirect:/";
	}

	//追加
	@GetMapping("/todo/edit/{id}")
	public String showEditTodo(@PathVariable Long id, Model model) {
		Todo todo = todoService.getTodoItemById(id);
		model.addAttribute("todoForm", todo);
		return "edit-todo";
	}

	@PostMapping("/todo/edit/{id}")
	public String editTodo(@PathVariable("id") Long id, @Valid @ModelAttribute("todoForm") Todo todo,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("todoForm", todo);
			return "edit-todo";
		}
		todoService.editTodo(id, todo.getTitle(), todo.getDescription(), todo.getTime_limit(), todo.getStatus());
		return "redirect:/"; //更新後に一覧のページにリダイレクト
	}

	@GetMapping("/todo/delete/{id}")
	public String deleteTodoItem(@PathVariable("id") Long id) {
		todoService.deleteTodoItem(id); //データベースからToDoアイテムを削除
		return "redirect:/"; //更新後に一覧のページにリダイレクト
	}
}
