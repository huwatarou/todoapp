package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public List<Todo> findAllTodos(){
		// 値の取得
		List<Map<String,Object>> todos = todoRepository.findAll();
		
		// Todo(Entity)クラスに値をセット
		List<Todo> todoList = new ArrayList<>();
		
		for(Map<String,Object> mapTodo : todos) {
			// Mapから値を取得
			Long id = ((Number) mapTodo.get("id")).longValue();
			String title = (String) mapTodo.get("title");
			String description = (String) mapTodo.get("description");
			Boolean status = (Boolean) mapTodo.get("status");
			
			// Todoエンティティを作成して値をセット
			Todo todo = new Todo();
			todo.setId(id);
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setStatus(status);
			
			// リストに追加
			todoList.add(todo);
		}
		return todoList;
	}
	
	//追加
	public void addTodo(String title,String description, Boolean status) {
		todoRepository.addTodo(title, description, status);
	}
	
	
	public Todo getTodoItemById(Long id){
		Map<String,Object> mapTodo = todoRepository.getTodoItemById(id);

			// Mapから値を取得
			Long mapTodo_id = ((Number) mapTodo.get("id")).longValue();
			String title = (String) mapTodo.get("title");
			String description = (String) mapTodo.get("description");
			Boolean status = (Boolean) mapTodo.get("status");
			
			// Todoエンティティを作成して値をセット
			Todo todo = new Todo();
			todo.setId(mapTodo_id);
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setStatus(status);

		return todo;
	}
	
	//追加
	public void editTodo(Long id,String title,String description, Boolean status) {
		todoRepository.addTodo(title, description, status);
	}
	
	public void deleteTodoItem(Long id) {
		todoRepository.deleteTodoItem(id);
	}

}

