package com.znsx.test.mongodb;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.scsvision.cms.util.nosql.MongoDbUtil;

public class MongoTest {
	public static void main(String[] args) {
//		MongoDatabase db = MongoDbUtil.getDatabase("maintain");
//		MongoCollection<Document> collection = db.getCollection("online_real");
//
//		// 创建基于StandardNumber的索引
//		ListIndexesIterable<Document> indexs = collection.listIndexes();
//		boolean createFlag = true;
//		for (Document index : indexs) {
//			Document key = (Document)index.get("key");
//			if (null != key.get("standardNumber")) {
//				createFlag = false;
//				break;
//			}
//				
//		}
//		if (createFlag) {
//			collection.createIndex(new BasicDBObject("standardNumber", 1));
//		}
		
		performanceTest();
	}
	
	public static void performanceTest() {
		MongoDatabase db = MongoDbUtil.getDatabase("performance");
		MongoCollection<Document> collection = db.getCollection("performance_test");
		
		byte[][] data = new byte[10][10240];
		// init
		for (int i = 0; i < 10; i++) {
			for (int j=0; j < 10240; j++) {
				data[i][j] = (byte) RandomUtils.nextInt(128);
			}
		}
		
		List<Document> documents = new LinkedList<Document>();
		for (int i = 0; i < 200000; i ++) {
			Document doc = new Document();
			for (int j = 0; j < 10; j++) {
				doc.put("data"+j, data[j]);
			}
			documents.add(doc);
		}
		
		long begin = System.currentTimeMillis();
		
		collection.insertMany(documents);
		
		long end = System.currentTimeMillis();
		System.out.println("use " + (end - begin) + "ms");
	}
}
