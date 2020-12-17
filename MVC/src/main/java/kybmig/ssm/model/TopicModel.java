package kybmig.ssm.model;

import kybmig.ssm.Utility;
import org.apache.catalina.User;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TopicModel extends BaseModel {
    private Integer id;
    private String content;

    private String title;
    private Integer userId;

    private ArrayList<TopicCommentModel> commentList;

    //用于记录帖子创建和更新时间
    private Long createdTime;
    private Long updatedTime;

    private UserModel user;

    private BoardModel board;

    private Integer boardId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ArrayList<TopicCommentModel> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<TopicCommentModel> commentList) {
        this.commentList = commentList;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }


    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public BoardModel getBoard() {
        return board;
    }

    public void setBoard(BoardModel board) {
        this.board = board;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long l = System.currentTimeMillis();
        System.out.println(l);
        Long l1 = 1607926983956L;
        Long l2 = l - l1;
        Date da = new Date(l2);
        String d2 = sdt.format(da);
        System.out.println(d2);
    }















}
