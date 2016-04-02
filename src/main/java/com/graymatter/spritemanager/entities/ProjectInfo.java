package com.graymatter.spritemanager.entities;

public class ProjectInfo {
    public String name = "SpriteManager_Default_Overwrite_Me";
    public String version = "SpriteManager_Default_Overwrite_Me";
    public String title = "SpriteManager_Default_Overwrite_Me";
    public String author = "SpriteManager_Default_Overwrite_Me";
    public String contact = "SpriteManager_Default_Overwrite_Me";
    public String homepage = "SpriteManager_Default_Overwrite_Me";
    public String description = "SpriteManager_Default_Overwrite_Me";

    public ProjectInfo(){
    	
    }
    
    public ProjectInfo(String name, String version, String title, String author, String contact, String homepage, String description){
        this.name = name;
        this.version = version;
        this.title = title;
        this.author = author;
        this.contact = contact;
        this.homepage = homepage;
        this.description = description;
    }
}