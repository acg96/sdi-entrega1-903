package com.uniovi.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Message implements Comparable<Message>{
	
	@Id
	@GeneratedValue
	private long id;
	
	private String author;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;
	

	public Message(String author, String text, Conversation conversation) {
		super();
		this.author= author;
		this.date= new Date();
		this.text= text;
		this.conversation= conversation;
	}
	
	public Message() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", author=" + author + ", date=" + date + ", text=" + text + "]";
	}

	@Override
	public int compareTo(Message o) {
		return this.getDate().compareTo(o.getDate());
	}
	
}