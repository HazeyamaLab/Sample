package model;

import java.time.LocalDateTime;

public class Post {
    private int postId;
    private String contributorId;
    private String userName;
    private int bbsId;
    private String title;
    private String body;
    private LocalDateTime createdAt;

    public Post(int postId,int contributorId,int bbsId,String title,String body,LocalDateTime createdAt) {
    }

    public Post() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContributorId() {
        return contributorId;
    }

    public void setContributorId(String contributorId) { this.contributorId = contributorId; }

    public String getUserName(){return userName;}

    public void setUserName(String userName){ this.userName = userName;}

    public int getBbsId() {
        return bbsId;
    }

    public void setBbsId(int bbsId) {
        this.bbsId = bbsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
