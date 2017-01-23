package com.swapasya.core;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Link;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.PostalAddress;
import com.google.appengine.api.datastore.Rating;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
//import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
//import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class General {
	
//	private final LocalServiceTestHelper helper =
//		      new LocalServiceTestHelper(
//		          // Set no eventual consistency, that way queries return all results.
//		          // https://cloud.google.com/appengine/docs/java/tools/localunittesting#Java_Writing_High_Replication_Datastore_tests
//		          new LocalDatastoreServiceTestConfig()
//		              .setDefaultHighRepJobPolicyUnappliedJobPercentage(0));
	
	
	private DatastoreService datastore;
	MemcacheService explicit ;
	
	public General (String nameSpace) {
		
//		helper.setUp();
		datastore = DatastoreServiceFactory.getDatastoreService();
		
		
		NamespaceManager.set(nameSpace);
		explicit = MemcacheServiceFactory.getMemcacheService(nameSpace);
		
		
	}
	
	
	public void createPersonMember (String personID) {
		
		Entity person = new Entity("Person", personID);
		
		person.setProperty("personID", personID);
		person.setUnindexedProperty("password", "myLibrary123");
		
		datastore.put(person);
		
	}
	
	
	public void addPersonDetailsTemp (String personID, String password, String firstName, String lastName,
			PostalAddress address, PhoneNumber mobileNo, String role) {
		
		
		Entity person = new Entity("Person", personID);  // ***mand
		
		
		//person.setProperty("personID", personID);
		person.setUnindexedProperty("password", password); // ***mand
		person.setProperty("firstName", firstName);  // ***mand
		person.setProperty("lastName", lastName);
		person.setProperty("address", address);
		person.setUnindexedProperty("mobileNoForNotification", mobileNo);
	
		//person.setProperty(PersonProp.booksInPossession, null);
		person.setProperty("role", role);  // ***mand
		// Transaction
		
		datastore.put(person);
				
		
	}
	
	
	
	public void addBookTemp ( String bookID, String bookName, String authour, ArrayList<Category> tags , String  categoryType) {
		
		Entity book = new Entity("Book", bookID);  // ***mand
	
		book.setProperty("bookID", bookID);    // ***mand
		book.setProperty("bookName", bookName);      
		book.setProperty("authour", authour);		
		book.setProperty("CategoryType", categoryType); // ***mand
		
		book.setProperty("borrowedBy", null); // keep key here
		//book.setProperty("issueType", issueType);  // to be added in tags
		book.setProperty("issuedType", null); // to be decided at runtime
		book.setProperty("issueDate", null); //Date
		book.setProperty("expectedReturnDate", null); //Date
		book.setProperty("tags", tags);
		//book.setProperty("tag2", tag2);
		book.setProperty("rating", null);

		// Transaction
		
		datastore.put(book);
		
	}
	
	
	
	
	

}
