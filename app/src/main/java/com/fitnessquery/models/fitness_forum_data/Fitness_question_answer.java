package com.fitnessquery.models.fitness_forum_data;

public class Fitness_question_answer
{
    private String id;

    private String user_name;

    private String question_text;

    private String answer_time;

    private String answer_text;

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

    public String getQuestion_text ()
    {
        return question_text;
    }

    public void setQuestion_text (String question_text)
    {
        this.question_text = question_text;
    }

    public String getAnswer_time ()
    {
        return answer_time;
    }

    public void setAnswer_time (String answer_time)
    {
        this.answer_time = answer_time;
    }

    public String getAnswer_text ()
    {
        return answer_text;
    }

    public void setAnswer_text (String answer_text)
    {
        this.answer_text = answer_text;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", user_name = "+user_name+", question_text = "+question_text+", answer_time = "+answer_time+", answer_text = "+answer_text+"]";
    }
}


