package com.softbistro.datatranslator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softbistro.datatranslator.components.entity.PostData;
import com.softbistro.datatranslator.service.TranslatorDataService;

@RestController
@RequestMapping(value = "/rest/translatordata/v1/")
public class TranslatorDataController {
	
	@Autowired
	private TranslatorDataService translatorDataService;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public PostData aggregateData(@RequestBody PostData postData) {
		return translatorDataService.aggregateData(postData);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public List<PostData> getAll() {
		return translatorDataService.getAll();
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = "application/json")
	public void drop() {
		translatorDataService.drop();
	}
}
