package application.dao.predicates;

import org.hibernate.jpa.criteria.predicate.BooleanExpressionPredicate;

import com.google.common.base.Predicate;

public class FeelerPredicate {

	// this should be a singleton

	public static FeelerPredicate instance;

	public FeelerPredicate() {
	}

	public static FeelerPredicate getInstance() {
		if (instance == null) {
			return instance = new FeelerPredicate();
		}
		return instance;
	}
	
	//TODO add query mysema maven dependency
//	
//	public Predicate getPredicateByFilter(){
//		
//	
//	}
}
