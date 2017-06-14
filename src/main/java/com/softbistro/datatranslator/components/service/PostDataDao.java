package com.softbistro.datatranslator.components.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.softbistro.datatranslator.components.entity.PostData;
import com.softbistro.datatranslator.components.interfaces.IPostTranslator;
import com.softbistro.datatranslator.service.ConnectToMongo;

@Repository
public class PostDataDao implements IPostTranslator {

	private static final Logger LOGGER = Logger.getLogger(PostDataDao.class);
	private static final String DATABASE = "streaming";

	@Autowired
	private ConnectToMongo connectToMongo;

	@Override
	public void save(PostData postTwitterData) {
		try {

			MongoDatabase db = connectToMongo.getMongoDbByName(DATABASE);
			MongoCollection<Document> collection = db.getCollection(PostData.TABLE_NAME);
			Document document = new Document();
			document.append("id", postTwitterData.getId());
			document.append("post", postTwitterData.getPost());

			collection.insertOne(document);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public List<PostData> getAll() {
		MongoDatabase db = connectToMongo.getMongoDbByName(DATABASE);
		MongoCollection<Document> collection = db.getCollection(PostData.TABLE_NAME);
		List<PostData> listPosts = new ArrayList<>();
		for (Document doc : collection.find()) {
			PostData post = new PostData();
			Integer id = (Integer) doc.get("id");
			post.setId(Integer.valueOf(id));
			post.setPost((String) doc.get("post"));
			listPosts.add(post);
		}
		return listPosts;
	}

	@Override
	public void clearCollection() {
		try {
			MongoDatabase db = connectToMongo.getMongoDbByName(DATABASE);
			MongoCollection<Document> collection = db.getCollection(PostData.TABLE_NAME);
			collection.drop();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public void saveBatch(List<PostData> posts) {

		try {

			MongoDatabase db = connectToMongo.getMongoDbByName(DATABASE);
			MongoCollection<Document> collection = db.getCollection(PostData.TABLE_NAME);

			List<Document> documentOfUsers = new ArrayList<>();
			for (PostData post : posts) {

				Document doc = new Document();
				doc.append("id", post.getId());
				doc.append("post", post.getPost());
				documentOfUsers.add(doc);
			}

			collection.insertMany(documentOfUsers);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

}
