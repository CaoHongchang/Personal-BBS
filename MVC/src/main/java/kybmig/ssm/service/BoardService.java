package kybmig.ssm.service;


import com.mysql.cj.jdbc.MysqlDataSource;
import kybmig.ssm.Utility;
import kybmig.ssm.mapper.BoardMapper;
import kybmig.ssm.mapper.TodoMapper;
import kybmig.ssm.model.BoardModel;
import kybmig.ssm.model.ModelFactory;
import kybmig.ssm.model.TodoModel;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }


//    public void update(Integer id, String content) {
//        TodoModel m = new TodoModel();
//        m.setId(id);
//        m.setContent(content);
//        boardMapper.updateTodo(m);
//    }


//    public TodoModel findById(Integer id) {
//        TodoModel todo = mapper.selectTodo(id);
//        return todo;
//    }

//    public  TodoModel findById(Integer id) {
//        ArrayList<TodoModel> todos = load();
//        for (int i = 0; i < todos.size(); i++) {
//            TodoModel e = todos.get(i);
//            if (e.getId().equals(id)) {
//                return e;
//            }
//        }
//        return null;
//    }

    public List<BoardModel> all() {
        return boardMapper.selectAllBoard();

    }

    public BoardModel selectBoardByName(String name) {
        return boardMapper.selectBoardByName(name);
    }

}
