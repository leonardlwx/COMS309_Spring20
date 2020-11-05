package com.example.StudyBuddy.LocalData;

public class User
{
    private String id;
    private String salt;
    private int password;
    private int tickets;
    private int usedTickets;

    public User()
    {

    }

    public User(String username, String salt, int password, int tickets, int usedTickets)
    {
        this.id = username;
        this.salt = salt;
        this.password = password;
        this.tickets = tickets;
        this.usedTickets = usedTickets;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String username)
    {
        this.id = username;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public int getPassword()
    {
        return password;
    }

    public void setPassword(int password)
    {
        this.password = password;
    }

    public int getTickets()
    {
        return tickets;
    }

    public void setTickets(int tickets)
    {
        this.tickets = tickets;
    }

    public int getUsedTickets()
    {
        return usedTickets;
    }

    public void setUsedtickets(int usedTickets)
    {
        this.usedTickets = usedTickets;
    }

    public void addTickets(int tickets)
    {
        this.tickets += tickets;
    }

    public void removeTicket()
    {
        --tickets;
        ++usedTickets;
    }
}
