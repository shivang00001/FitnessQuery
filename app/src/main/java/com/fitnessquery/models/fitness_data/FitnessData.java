package com.fitnessquery.models.fitness_data;

public class FitnessData
{
    private String message;

    private Fitness_feed[] fitness_feed;

    private String status;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public Fitness_feed[] getFitness_feed ()
    {
        return fitness_feed;
    }

    public void setFitness_feed (Fitness_feed[] fitness_feed)
    {
        this.fitness_feed = fitness_feed;
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
        return "ClassPojo [message = "+message+", fitness_feed = "+fitness_feed+", status = "+status+"]";
    }
}

