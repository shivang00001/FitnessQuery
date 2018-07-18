package com.fitnessquery.models.fitness_data;

public class Post_images
{
    private String id;

    private String post_image_url;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPost_image_url ()
    {
        return post_image_url;
    }

    public void setPost_image_url (String post_image_url)
    {
        this.post_image_url = post_image_url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", post_image_url = "+post_image_url+"]";
    }
}
