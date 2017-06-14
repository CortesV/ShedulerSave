package com.softbistro.datatranslator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.softbistro.datatranslator.components.entity.PostData;
import com.softbistro.datatranslator.components.interfaces.IPostTranslator;

@Service
@EnableScheduling
public class TranslatorDataService {

	@Autowired
	private IPostTranslator iPostTranslator;

	private static List<PostData> aggregateData;

	public TranslatorDataService() {
		aggregateData = new ArrayList<>();
	}

	public PostData aggregateData(PostData postData) {
		aggregateData.add(postData);
		System.out.println(aggregateData.size());
		return postData;
	}

	public List<PostData> getAll() {
		return iPostTranslator.getAll();
	}

	public void drop() {
		iPostTranslator.clearCollection();
	}

	@Scheduled(fixedRate = 20000)
	public void saveBatch(){
		iPostTranslator.saveBatch(aggregateData);
		List<PostData> savedData = new ArrayList<>(aggregateData);
		aggregateData.clear();
		for(PostData post : savedData){
			System.out.println(post.toString());
		}
	}
}
