package com.softbistro.datatranslator.components.entity;

import java.io.Serializable;
import java.util.List;

public class PostData implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String TABLE_NAME = "twitter_post";

	private Integer id;
	private String post;

	public PostData(String post) {
		this.post = post;
	}

	public PostData() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "PostTwitterData [id=" + id + ", post=" + post + "]";
	}
}