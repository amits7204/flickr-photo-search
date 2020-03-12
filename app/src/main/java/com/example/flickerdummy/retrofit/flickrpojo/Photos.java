package com.example.flickerdummy.retrofit.flickrpojo;

import java.util.List;

public class Photos {

    private int page;

    private int pages;

    private int perpage;

    private String total;

    private List<Photo> photo;

    public void setPage(int page){
        this.page = page;
    }
    public int getPage(){
        return this.page;
    }
    public void setPages(int pages){
        this.pages = pages;
    }
    public int getPages(){
        return this.pages;
    }
    public void setPerpage(int perpage){
        this.perpage = perpage;
    }
    public int getPerpage(){
        return this.perpage;
    }
    public void setTotal(String total){
        this.total = total;
    }
    public String getTotal(){
        return this.total;
    }
    public void setPhoto(List<Photo> photo){
        this.photo = photo;
    }
    public List<Photo> getPhoto(){
        return this.photo;
    }

}
