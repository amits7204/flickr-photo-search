package com.example.flickerdummy.retrofit.flickrpojo;

public class Photo {

    private String id;

    private String owner;

    private String secret;

    private String server;

    private int farm;

    private String title;

    private int ispublic;

    private int isfriend;

    private int isfamily;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    public String getOwner(){
        return this.owner;
    }
    public void setSecret(String secret){
        this.secret = secret;
    }
    public String getSecret(){
        return this.secret;
    }
    public void setServer(String server){
        this.server = server;
    }
    public String getServer(){
        return this.server;
    }
    public void setFarm(int farm){
        this.farm = farm;
    }
    public int getFarm(){
        return this.farm;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setIspublic(int ispublic){
        this.ispublic = ispublic;
    }
    public int getIspublic(){
        return this.ispublic;
    }
    public void setIsfriend(int isfriend){
        this.isfriend = isfriend;
    }
    public int getIsfriend(){
        return this.isfriend;
    }
    public void setIsfamily(int isfamily){
        this.isfamily = isfamily;
    }
    public int getIsfamily(){
        return this.isfamily;
    }

}
