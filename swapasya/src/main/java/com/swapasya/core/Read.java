package com.swapasya.core;



import java.util.ArrayList;
import java.util.HashMap;
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
import com.swapasya.dataTypes.BookTitleProp;
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
	
	
	
	// AssignList, waitlist 
	
	public ArrayList<HashMap<String, String>> getAssignList (String personID) {
		List<Entity> assignList = null;
		//	person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
			Filter f = new FilterPredicate("personID", FilterOperator.EQUAL, personID);
	    	Query q = new Query(NameKinds.AssignList).setFilter(f).setKeysOnly();
	    	assignList = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
	    	
	    	ArrayList<HashMap<String, String>> hashMapArray = new ArrayList<>();
	    	
	    	for (Entity e : assignList) {
	    		HashMap<String, String> hashMap = new HashMap<>();
	    		Key parentbookTitleKey = e.getKey().getParent();
	    		Entity parentbookTitle;
				try {
					parentbookTitle = datastore.get(parentbookTitleKey);
					
					hashMap.put(BookTitleProp.bookTitleID, (String) parentbookTitleKey.getName());
					hashMap.put(BookTitleProp.bookName, (String) parentbookTitle.getProperty(BookTitleProp.bookName));
					hashMap.put(BookTitleProp.author, (String) parentbookTitle.getProperty(BookTitleProp.author));
		    		hashMapArray.add(hashMap);
				} catch (EntityNotFoundException e2) {
					// TODO Auto-generated catch block
					//e2.printStackTrace();
				} // get titleID
	    		
	    		
	    	}
	    	
		return hashMapArray;
	}
	
	
	public ArrayList<HashMap<String, String>> getWaitList (String personID) {
		List<Entity> waitList = null;
		//	person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
			Filter f = new FilterPredicate("personID", FilterOperator.EQUAL, personID);
	    	Query q = new Query(NameKinds.WaitList).setFilter(f).setKeysOnly();
	    	waitList = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
	    	
	    	ArrayList<HashMap<String, String>> hashMapArray = new ArrayList<>();
	    	
	    	for (Entity e : waitList) {
	    		HashMap<String, String> hashMap = new HashMap<>();
	    		Key parentbookTitleKey = e.getKey().getParent();
	    		Entity parentbookTitle;
				try {
					parentbookTitle = datastore.get(parentbookTitleKey);
					
					hashMap.put(BookTitleProp.bookTitleID, (String) parentbookTitleKey.getName());
					hashMap.put(BookTitleProp.bookName, (String) parentbookTitle.getProperty(BookTitleProp.bookName));
					hashMap.put(BookTitleProp.author, (String) parentbookTitle.getProperty(BookTitleProp.author));
		    		hashMapArray.add(hashMap);
				} catch (EntityNotFoundException e2) {
					// TODO Auto-generated catch block
					//e2.printStackTrace();
				} // get titleID
	    		
	    		
	    	}
	    	
		return hashMapArray;
	}
	
	
	
	
	
	public Entity getPerson (String personID) {
		Entity person = null;
		try {
			person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}
	
	
	public Entity getBook (String bookID) {
		Entity book = null;
		try {
		//	person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
			Filter f = new FilterPredicate(BookProp.bookID, FilterOperator.EQUAL, bookID);
	    	Query q = new Query(NameKinds.Book).setFilter(f);
	    	book = datastore.prepare(q).asSingleEntity();
	    	if (book==null)
	    		throw new Exception();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
	
	public Entity getBookTitle (String titleID) {
		Entity bookTitle = null;
		try {
			bookTitle = datastore.get(KeyFactory.createKey(NameKinds.BookTitle, titleID));
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookTitle;
	}
	
	
	
	public Entity getBookTitle (Entity book) {
		Entity bookTitle = null;
		try {
			bookTitle = datastore.get(book.getParent());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookTitle;
	}
	
	
	public List<Entity> getHistory (String personID) throws TransactionFailed {
		
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
		
//		for (Entity e : results) {
//			
//			System.out.println("One " + e.getProperty(BookProp.bookName) + " " + e.getProperty(TransactionHistoryProp.dateOfIssue) + "" + e.getProperty(TransactionHistoryProp.person));
//		//	System.out.println("One " + e.getProperty(PersonProp.address));
//			
//		}
		
		return results;
		
		
	}
	
	
	
	public List<Entity> getHistoryForBook (String bookID) throws TransactionFailed {
		
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
		
		Query q = new Query(NameKinds.TransactionHistory);
		
		// Does not work
		Filter f = new FilterPredicate(TransactionHistoryProp.bookID, FilterOperator.EQUAL, bookID);

		q.setFilter(f);
		q.addSort(TransactionHistoryProp.dateOfIssue, SortDirection.ASCENDING);
			
		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		
//		for (Entity e : results) {
//			
//			System.out.println("One " + e.getProperty(TransactionHistoryProp.bookID) + " " + e.getProperty(TransactionHistoryProp.dateOfIssue) + "" + e.getProperty(TransactionHistoryProp.person));
//		//	System.out.println("One " + e.getProperty(PersonProp.address));
//			
//		}
		
		return results;
	}

}
