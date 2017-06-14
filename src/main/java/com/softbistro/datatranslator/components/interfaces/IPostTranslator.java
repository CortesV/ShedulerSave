package com.softbistro.datatranslator.components.interfaces;

import java.util.List;

import com.softbistro.datatranslator.components.entity.PostData;

public interface IPostTranslator {

	public void save(PostData postData);
	public List<PostData> getAll();
	public void clearCollection();
	public void saveBatch(List<PostData> posts);
}
