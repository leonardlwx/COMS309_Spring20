package com.example.addingpet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Pet 
{
	@Id
	private Integer id;
	private String name;
	private String type;
	
	@ManyToOne//(cascade = {CascadeType.ALL})
	private User owner;
	
	public Pet()
	{
		
	}
	
	public Pet(String name, String type, User owner)
	{
		super();
		id = (name + type + owner).hashCode();
		this.name = name;
		this.type = type;
		this.owner = owner;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
	
	public User getOwner()
	{
		return owner;
	}
	
	public void generateId()
	{
		id = (name + type + owner.getId()).hashCode();
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setOwner(User owner)
	{
		this.owner = owner;
	}
	
	@Override
	public boolean equals (Object o)
	{
		return (id == ((Pet)o).id);	
	}
}
