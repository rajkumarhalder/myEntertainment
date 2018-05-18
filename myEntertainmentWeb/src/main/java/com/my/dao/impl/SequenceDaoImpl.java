package com.my.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.my.dao.SequenceDao;
import com.my.model.SequenceId;

@Component
public class SequenceDaoImpl implements SequenceDao{
	
	@Autowired
	private MongoOperations mongoOperation;

	@Override
	public long getNextSequenceId(String key) {
		//get sequence id
		Query query = new Query(Criteria.where("_id").is(key));

		//increase sequence id by 1
		Update update = new Update();
		update.inc("sequence", 1);

		//return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		//this is the magic happened.
		SequenceId seqId =mongoOperation.findAndModify(query, update, options, SequenceId.class);


		return seqId.getSequence();
	}

}
