package com.example.addingpet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User 
{
	@Id
	private String id;
	
	public User() 
	{
		
	}
	
	public User(String id) 
	{
		this.id = id;
	}
	
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}	
	
	@Override
	public boolean equals (Object o)
	{
		return id.equals(((User)o).id);
	}
}
