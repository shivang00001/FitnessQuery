package com.fitnessquery.models.fitness_forum_data;

public class FitnessForumData
{
    private String message;

    private Fitness_question_answer[] fitness_question_answer;

    private String status;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public Fitness_question_answer[] getFitness_question_answer ()
    {
        return fitness_question_answer;
    }

    public void setFitness_question_answer (Fitness_question_answer[] fitness_question_answer)
    {
        this.fitness_question_answer = fitness_question_answer;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", fitness_question_answer = "+fitness_question_answer+", status = "+status+"]";
    }
}

