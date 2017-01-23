package com.swapasya.core;



import java.util.List;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.swapasya.dataTypes.BookProp;
import com.swapasya.dataTypes.NameKinds;
import com.swapasya.dataTypes.PersonProp;
import com.swapasya.dataTypes.TransactionHistoryProp;

public class Read {
	
	private DatastoreService datastore;
	MemcacheService explicit ;
	
	public Read (String nameSpace) {
		
//		helper.setUp();
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		
		NamespaceManager.set(nameSpace);
		explicit = MemcacheServiceFactory.getMemcacheService(nameSpace);
		
		
	}
	
	
	public void getHistory (String personID) throws TransactionFailed {
		
		Entity person = null;
		Key k ;
		try {
		// person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
			k = KeyFactory.createKey(NameKinds.Person, personID);
		} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("EntityNotFound BookID or PersonID is invalid");
    		throw new TransactionFailed ();
    	}
		
		// person.
		System.out.println("one1 " + k);
		try {
			person = datastore.get(k);
		} catch (EntityNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("one1 " + person.getProperty(PersonProp.address));
		
		Query q = new Query(NameKinds.TransactionHistory, k);
		
		// Does not work
		Query q2 = new Query(NameKinds.TransactionHistory).setAncestor(k).addSort(TransactionHistoryProp.dateOfIssue, SortDirection.ASCENDING);
		
		Query q3 = new Query(NameKinds.TransactionHistory, k).addSort(TransactionHistoryProp.dateOfIssue, SortDirection.ASCENDING);
		
		List<Entity> results = datastore.prepare(q3).asList(FetchOptions.Builder.withDefaults());
		
		for (Entity e : results) {
			
			System.out.println("One " + e.getProperty(BookProp.bookName) + " " + e.getProperty(TransactionHistoryProp.dateOfIssue) + "" + e.getProperty(TransactionHistoryProp.person));
		//	System.out.println("One " + e.getProperty(PersonProp.address));
			
		}
		
		
		
		
	}
	
	
	
	public void getHistoryForBook (String bookID) throws TransactionFailed {
		
		Entity book = null;
		Key k ;
		try {
		// person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
			k = KeyFactory.createKey(NameKinds.Book, bookID);
		} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("EntityNotFound BookID or PersonID is invalid");
    		throw new TransactionFailed ();
    	}
		

		System.out.println("get Book history of key : " + k);
		try {
			book = datastore.get(k);
		} catch (EntityNotFoundException e1) {

			e1.printStackTrace();
		}
		System.out.println("Book history of book " + book.getProperty(BookProp.bookName));
		
		Query q = new Query(NameKinds.TransactionHistory);
		
		// Does not work
		Filter f = new FilterPredicate(TransactionHistoryProp.bookID, FilterOperator.EQUAL, bookID);

		q.setFilter(f);
		q.addSort(TransactionHistoryProp.dateOfIssue, SortDirection.ASCENDING);
			
		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		
		for (Entity e : results) {
			
			System.out.println("One " + e.getProperty(TransactionHistoryProp.bookID) + " " + e.getProperty(TransactionHistoryProp.dateOfIssue) + "" + e.getProperty(TransactionHistoryProp.person));
		//	System.out.println("One " + e.getProperty(PersonProp.address));
			
		}
}

}
