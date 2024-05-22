package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * データベースから全てのTodoを取得するメソッド
	 */
	public List<Map<String,Object>> findAll(){
		List<Map<String,Object>> todos;
		String query = "SELECT id, title, description, status, DATE_FORMAT(time_limit, '%Y-%m-%T') AS time_limit FROM todos";
		try {
			todos = jdbcTemplate.queryForList(query);
		}catch(Exception e) {
			//SQLクエリの実行中に発生した予期しないエラーを補足
			return null;
		}
		return todos;
	}
	
	/*
	 * 新しいTodoをデータベースに追加するメソッド
	 */
	public void addTodo(String title, String description, Boolean status,String time_limit) {
		String query =  "INSERT INTO todos (title, description, status,time_limit) VALUES(?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d %T')) ";
		jdbcTemplate.update(query, title, description, status,time_limit);
	}
	
	/*
	 * IDから1コードを取得するメソッド
	 */
	
	public Map<String,Object>getTodoItemById(Long id){
		  String query = "SELECT id, title, description, status, DATE_FORMAT(time_limit, '%Y-%m-%T') AS time_limit FROM todos WHERE id = ?";		try {
			return jdbcTemplate.queryForMap(query,id);
		}catch(Exception e) {
			//SQLクエリの実行中に発生した予期しないエラーを補足
			return null;
		}
		
	}
	
	/**
	 * 修正するメソッド
	 */
	public void editTodo(Long id,String title,String description,String time_limit,Boolean status) {
		String query = "UPDATE todos SET title = ?, description = ?, time_limit = STR_TO_DATE(?, '%Y-%m-%d %T'),status = ? WHERE id = ?";
		jdbcTemplate.update(query,title,description,time_limit,status,id);
	}
	
	/*
	 * 削除するメソッド
	 */
	public void deleteTodoItem(Long id) {
		String sql = "DELETE FROM todos WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
}