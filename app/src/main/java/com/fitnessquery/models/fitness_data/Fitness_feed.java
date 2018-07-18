package com.fitnessquery.models.fitness_data;

public class Fitness_feed
{
    private String id;

    private String user_name;

    private String post_text;

    private Post_images[] post_images;

    private String post_time;

    private String user_image;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUser_name ()
    {
        return user_name;
    }

    public void setUser_name (String user_name)
    {
        this.user_name = user_name;
    }

    public String getPost_text ()
    {
        return post_text;
    }

    public void setPost_text (String post_text)
    {
        this.post_text = post_text;
    }

    public Post_images[] getPost_images ()
    {
        return post_images;
    }

    public void setPost_images (Post_images[] post_images)
    {
        this.post_images = post_images;
    }

    public String getPost_time ()
    {
        return post_time;
    }

    public void setPost_time (String post_time)
    {
        this.post_time = post_time;
    }

    public String getUser_image ()
    {
        return user_image;
    }

    public void setUser_image (String user_image)
    {
        this.user_image = user_image;
    }

}


