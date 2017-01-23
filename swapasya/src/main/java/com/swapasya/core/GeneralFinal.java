package com.swapasya.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Link;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.PostalAddress;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Rating;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.User;
import com.swapasya.dataTypes.BookProp;
import com.swapasya.dataTypes.BookTitleProp;
import com.swapasya.dataTypes.GenProp;
import com.swapasya.dataTypes.NameKinds;
import com.swapasya.dataTypes.PersonProp;
import com.swapasya.dataTypes.RulesProp;
import com.swapasya.dataTypes.TransactionHistoryProp;

public class GeneralFinal {

	private DatastoreService datastore;
	MemcacheService explicit;

	public GeneralFinal(String nameSpace) {

		// helper.setUp();
		datastore = DatastoreServiceFactory.getDatastoreService();

		NamespaceManager.set(nameSpace);
		explicit = MemcacheServiceFactory.getMemcacheService(nameSpace);

	}

	public void addPersonDetails(String personID, String password, String firstName, String lastName, User gUser,
			PostalAddress address, Email emailId, PhoneNumber mobileNo, PhoneNumber contactNo, String degree,
			String branch, int courseyear, String division, int rollNo, ArrayList<String> booksInPossesion,
			boolean isBookBankEnabled, String bookBankID, String role, Date admissionDate) {

		Entity person = new Entity(NameKinds.Person, personID);

		// person.setProperty("personID", personID);
		person.setUnindexedProperty(PersonProp.password, password);
		person.setProperty(PersonProp.personName, firstName);
		// person.setProperty("lastName", lastName);
		// person.setProperty("gUser", gUser);
		person.setProperty(PersonProp.address, address);
		person.setUnindexedProperty(PersonProp.emailId, emailId);
		person.setUnindexedProperty(PersonProp.mobileNo, mobileNo);
		person.setUnindexedProperty(PersonProp.contactNo, contactNo);
		person.setProperty(PersonProp.degree, degree);
		person.setProperty(PersonProp.branch, branch);
		person.setProperty(PersonProp.courseyear, courseyear);
		// person.setProperty(PersonProp.d, division);
		person.setUnindexedProperty(PersonProp.rollNo, division + rollNo);

		// person.setProperty("isBookBankEnabled", isBookBankEnabled);
		// person.setProperty("bookBankID", bookBankID); //key or ID
		person.setProperty(PersonProp.role, role);
		person.setProperty(PersonProp.admissionDate, admissionDate);
		// Transaction

		datastore.put(person);

	}

	public void addBookTitle(String bookTitleID, String isbnNumber, String bookName, String authour,
			String categoryType, String publication, float price, Date purchaseDate, String bindingType,
			ArrayList<Category> tags, int noOfPages, String language, int noOfRenewAllowed, Rating rating,
			Link imgPath) {

		Entity book = new Entity(NameKinds.BookTitle, bookTitleID);

		book.setProperty(BookTitleProp.bookTitleID, bookTitleID);
		book.setProperty(BookTitleProp.isbnNumber, isbnNumber); // long
		book.setProperty(BookTitleProp.bookName, bookName);
		book.setProperty(BookTitleProp.author, authour);
		book.setProperty(BookTitleProp.publication, publication);

		book.setUnindexedProperty(BookTitleProp.bindingType, bindingType);

		book.setProperty(BookTitleProp.tags, tags);

		book.setUnindexedProperty(BookTitleProp.noOfPages, noOfPages);
		book.setProperty(BookTitleProp.language, language);

		book.setProperty(BookTitleProp.rating, null);
		book.setUnindexedProperty(BookTitleProp.imgPath, imgPath);
		// Transaction

		datastore.put(book);

	}
	
	// if personID does not exists :
	// KeyFactory.createKey created any random key not null
	// datastore.get throws exception EntityNotFoundException
	
	// if property does not exists it is returns null ( no exception thrown)

	
	
    		

	private void addBookInBookTitle(Key bookTitleKey, String bookID, String isbnNumber, String bookName, String authour,
			String categoryType, String publication, float price, Date purchaseDate, String bindingType,
			ArrayList<Category> tags, int noOfPages, String language, int noOfRenewAllowed, Rating rating,
			Link imgPath) {

		Entity book = new Entity(NameKinds.Book, bookID, bookTitleKey);

		book.setProperty(BookProp.bookID, bookID);

		book.setProperty(BookProp.price, price); // float
		book.setProperty(BookProp.purchaseDate, purchaseDate); // Date

		book.setProperty(BookProp.borrowedBy, null); // keep key here
		book.setProperty(BookProp.categoryType, categoryType); // ***mand

		book.setProperty(BookProp.issuedType, null); // to be decided at runtime
		book.setProperty(BookProp.issueDate, null); // Date
		book.setProperty(BookProp.expectedReturnDate, null); // Date

		// book.setProperty("noOfRenewAllowed", noOfRenewAllowed);

		// Transaction

		datastore.put(book);

	}

	public void addBook(String bookID, String isbnNumber, String bookName, String authour, String categoryType,
			String publication, float price, Date purchaseDate, String bindingType, ArrayList<Category> tags,
			int noOfPages, String language, int noOfRenewAllowed, Rating rating, Link imgPath) {

		Query q = new Query(NameKinds.BookTitle);
		// q.addFilter(BookTitleProp.bookName, FilterOperator.EQUAL,
		// bookName).addFilter(BookTitleProp.author, FilterOperator.EQUAL,
		// authour);
		Filter f = new FilterPredicate(BookTitleProp.bookName, FilterOperator.EQUAL, bookName);
		Filter f2 = new FilterPredicate(BookTitleProp.author, FilterOperator.EQUAL, authour);
		CompositeFilter fil = CompositeFilterOperator.and(f, f2);
		q.setFilter(fil);

		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

		String bookTitleID = "T_" + bookID;
		Key parentKey = null;

		if (results == null || results.isEmpty()) {

			addBookTitle(bookTitleID, isbnNumber, bookName, authour, categoryType, publication, price, purchaseDate,
					bindingType, tags, noOfPages, language, noOfRenewAllowed, rating, imgPath);

			parentKey = KeyFactory.createKey(NameKinds.BookTitle, bookTitleID);
			System.out.println("Not present : creating new book title" );
		} else {
			
			System.out.println("ALready present : " + results.get(0).getProperty(BookTitleProp.bookTitleID));

			parentKey = results.get(0).getKey();

		}

		addBookInBookTitle(parentKey, bookID, isbnNumber, bookName, authour, categoryType, publication, price,
				purchaseDate, bindingType, tags, noOfPages, language, noOfRenewAllowed, rating, imgPath);
		
		
		/// Extra 
		Query q2 = new Query(NameKinds.Book, parentKey).addSort(BookProp.bookID, SortDirection.DESCENDING);
		List<Entity> results2 = datastore.prepare(q2).asList(FetchOptions.Builder.withDefaults());
		System.out.println("Same books : ");
		for (Entity temp : results2) {
			System.out.println("      : " + temp.getProperty(BookProp.bookID));
		}
		
	}
	
	
	public void addBookInBookTitle(String bookTitleID, String bookID, String bookName, String authour,
			String categoryType, float price, Date purchaseDate) throws TransactionFailed {

		
		
		Entity bookTitle;
		try {
			bookTitle = datastore.get(KeyFactory.createKey(NameKinds.BookTitle, bookTitleID));
		} catch (EntityNotFoundException e) {
			System.out.println("Title is not available");
			throw new TransactionFailed();
		}
		
		String titleName = (String) bookTitle.getProperty(BookTitleProp.bookName);
		String titleAuthour = (String) bookTitle.getProperty(BookTitleProp.author);
		
		if (titleName!=bookName || titleAuthour!=authour) {
			System.out.println("TitleBook is not same as given book");
			throw new TransactionFailed();
		}
		
		Entity book = new Entity(NameKinds.Book, bookID, bookTitle.getKey());

		book.setProperty(BookProp.bookID, bookID);

		book.setProperty(BookProp.price, price); // float
		book.setProperty(BookProp.purchaseDate, purchaseDate); // Date

		book.setProperty(BookProp.borrowedBy, null); // keep key here
		book.setProperty(BookProp.categoryType, categoryType); // ***mand

		book.setProperty(BookProp.issuedType, null); // to be decided at runtime
		book.setProperty(BookProp.issueDate, null); // Date
		book.setProperty(BookProp.expectedReturnDate, null); // Date

		datastore.put(book);

	}
	

	// this will be without any namespace.... YET TO CREATEEEEEE
	public void libraryAdmin(String collegeID) {

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity libraryAdmin = new Entity("LibraryAdmin", collegeID);

		libraryAdmin.setProperty("collegeID", collegeID);
		// libraryAdmin.setProperty("collegeName", collegeName);
		// libraryAdmin.setProperty("collegeAddress", collegeAddress);

		datastore.put(libraryAdmin);

	}
	
	public void roleDefine (String roleName, String readerType) { //personRole
		Entity roleDefine = new Entity("RoleDefine", roleName);
		roleDefine.setUnindexedProperty("readerType", readerType);
		
		datastore.put(roleDefine);
	}

	public void issueWiseRules(String readerType, String issueType, int bookQuantityLimit, 
			int dayLimitForEachBook , float finePerDay) {

		Entity issueWiseRules = new Entity(NameKinds.Rules, readerType + "-" + issueType);

		issueWiseRules.setProperty(RulesProp.readerType_BkCatORissueType, readerType + "-" + issueType);
		issueWiseRules.setProperty(RulesProp.dayLimit, (int) dayLimitForEachBook);
		// Not always necessary
		issueWiseRules.setProperty(RulesProp.maxQuantity, (int) bookQuantityLimit); 
		issueWiseRules.setProperty(RulesProp.finePerDay, finePerDay);

		datastore.put(issueWiseRules);

	}
	
	public void issueWiseRules (String readerType, String issueType,  int dayLimitForEachBook , float finePerDay) {
		
		Entity issueWiseRules = new Entity(NameKinds.Rules, readerType +"-"+ issueType);
		
		issueWiseRules.setProperty(RulesProp.readerType_BkCatORissueType, readerType +"-"+ issueType);
		issueWiseRules.setProperty(RulesProp.dayLimit,(int) dayLimitForEachBook);
		
		//specific to person ?
		
		datastore.put(issueWiseRules);
		
	}

	public void categoryWiseRules(String readerType, String bookCategory,  int bookQuantityLimit,
			int dayLimitForEachBook, int maxTotalQuantity, float finePerDay) {

		Entity categoryWiseRules = new Entity(NameKinds.Rules, readerType + "-" + bookCategory);

		categoryWiseRules.setProperty(RulesProp.readerType_BkCatORissueType, readerType + "-" + bookCategory);
		categoryWiseRules.setProperty(RulesProp.dayLimit, (int) dayLimitForEachBook);
		categoryWiseRules.setProperty(RulesProp.maxQuantity, (int) bookQuantityLimit);
		categoryWiseRules.setProperty(RulesProp.maxTotalQuantity, (int) maxTotalQuantity); //
		categoryWiseRules.setProperty(RulesProp.finePerDay, finePerDay);

		datastore.put(categoryWiseRules);

	}
	
	
	public void categoryWiseRules (String readerType, String bookCategory,  int bookQuantityLimit , int dayLimitForEachBook, float finePerDay ) {
		 
		Entity categoryWiseRules = new Entity(NameKinds.Rules, readerType +"-"+ bookCategory);
		
		categoryWiseRules.setProperty(RulesProp.readerType_BkCatORissueType, readerType +"-"+ bookCategory);
		categoryWiseRules.setProperty(RulesProp.dayLimit,(int) dayLimitForEachBook);
		categoryWiseRules.setProperty(RulesProp.maxQuantity, (int) bookQuantityLimit);
		categoryWiseRules.setProperty(RulesProp.finePerDay, finePerDay);
		
		datastore.put(categoryWiseRules);
		
	}
	
	
	public int availableBooksInTitle (Key bookTitleKey) {
		Filter f = new FilterPredicate(BookProp.borrowedBy, FilterOperator.EQUAL, null);
		Query q = new Query(NameKinds.Book, bookTitleKey).setFilter(f).setKeysOnly();
		int results = datastore.prepare(q).countEntities();
		return results;
	}
	
	
	public int numberOfAssigneeToTitle (Key bookTitleKey) {
		Query q2 = new Query(NameKinds.AssignList, bookTitleKey).setKeysOnly();
		int results2 = datastore.prepare(q2).countEntities();
		return results2;
	}
	
	
	
	/*
	public void addMeToWaitList ( String personID , String bookTitleID) {
		
		Entity person;
		Entity bookTitle;
		
		TransactionOptions options = TransactionOptions.Builder.withXG(true);
	    Transaction txn = datastore.beginTransaction(options);
	    
	    try {
	    
		try {
    		Key personKey = KeyFactory.createKey(NameKinds.Person, personID);
//    		Filter f = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, personKey);
//    		Query q = new Query(NameKinds.Person).setFilter(f).setKeysOnly();
//
//    		int x = datastore.prepare(q).countEntities(FetchOptions.Builder.withDefaults());
//    		List<Entity> resultsSet = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
//    		for (Entity a : resultsSet) {
//    			System.out.println("iterator : " + a.getProperty(PersonProp.personName) + " \n  " + x);
//    		}
//    		System.out.println(" int : " + x);
//    		if (personKey==null)
//    			System.out.println("Person key is null");
//    		else
//    			System.out.println("Person key " + personKey);
//    		System.out.println("key step done");
    		person = datastore.get(personKey);
    		Key bookTitleKey = KeyFactory.createKey(NameKinds.BookTitle, personID);
    		bookTitle = datastore.get(bookTitleKey);
    		String prop = (String) person.getProperty(bookTitleID);
    		System.out.println("prop : " + prop);
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("BookTitleID or PersonID is invalid");
    		throw new TransactionFailed ();
    	}
		
		List<String> waitList = (List<String>) bookTitle.getProperty("WaitList");
		
		if (waitList==null) {
			waitList = new ArrayList<String>();
		} else {
			if (waitList.contains(personID)) {
				// this person is already in wait list of book
				throw new TransactionFailed ();
			}
			waitList.add(personID);
		}
		
		bookTitle.setProperty("WaitList", waitList);
		
		datastore.put(txn, bookTitle);
		
		
		
		
	    } catch (Exception e) {
	    	
	    } finally {
	    	if (txn.isActive()) {
		          txn.rollback();
		     }
	    }
		
	}
	
	*/
	
public void addMeToWaitORAssignList ( String personID , String bookTitleID) throws TransactionFailed {
		
		Entity person;
		Entity bookTitle;
		Key bookTitleKey;
		
		try {
    		Key personKey = KeyFactory.createKey(NameKinds.Person, personID);
    		person = datastore.get(personKey);
    		bookTitleKey = KeyFactory.createKey(NameKinds.BookTitle, bookTitleID);
    		bookTitle = datastore.get(bookTitleKey);
		} catch (Exception e) {
			e.printStackTrace();
    		System.out.println(e.toString() + " BookTitleID or PersonID is invalid");
    		throw new TransactionFailed ();
    	}
		
		// note : set default to bull : BookProp.borrowedBy
		
		try {
			datastore.get(bookTitleKey.getChild(NameKinds.AssignList, personID));
			System.out.println("Already added to AssignList");
			return;
		} catch (Exception e) {
			
		}
		
		try {
			datastore.get(bookTitleKey.getChild(NameKinds.WaitList, personID));
			System.out.println("Already added to WaitList");
			return;
		} catch (Exception e) {
			
		}
		
		
		Filter f = new FilterPredicate(BookProp.borrowedBy, FilterOperator.EQUAL, null);
		Query q = new Query(NameKinds.Book, bookTitleKey).setFilter(f).setKeysOnly();
		int results = datastore.prepare(q).countEntities();
		
		
		Query q2 = new Query(NameKinds.AssignList, bookTitleKey).setKeysOnly();
		int results2 = datastore.prepare(q2).countEntities();
		
		System.out.println(" results : " + results  + " " + results2);
		Date d = new Date();
		
		TransactionOptions options = TransactionOptions.Builder.withXG(true);
	    Transaction txn = datastore.beginTransaction(options);
		
		try {
		    if (results>results2) {
		    	System.out.println("creating assign list");
				Entity assigned = new Entity(NameKinds.AssignList, personID, bookTitleKey);
				assigned.setUnindexedProperty(GenProp.Date, d);
				datastore.put(txn, assigned);
			} else {
				Entity waitlisted = new Entity(NameKinds.WaitList, personID, bookTitleKey);
				waitlisted.setIndexedProperty(GenProp.Date, d);
				datastore.put(txn, waitlisted);
			}
		    txn.commit();
		} catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	if (txn.isActive()) {
		          txn.rollback();
		     }
	    }
		
		
		
		
	}
	


	public void changeWaitAndAssignList (Entity assigned, Key parentBookTitleKey, Transaction txn) {
		if (assigned!=null) {
			datastore.delete(txn, assigned.getKey());
			
			Filter f = new FilterPredicate(BookProp.borrowedBy, FilterOperator.EQUAL, null);
	//		new FilterPre
			Query q = new Query(NameKinds.WaitList, parentBookTitleKey).addSort(GenProp.Date, SortDirection.DESCENDING).setKeysOnly();
			// FOllowing is used in returnbook also
			List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(1));
			
			if (!results.isEmpty()) {
				Entity waitlisted = results.get(0);
				String newAssigneeID = results.get(0).getKey().getName();
				datastore.delete(txn, waitlisted.getKey());
				
				System.out.println(" id new assignee : " + newAssigneeID);
				
				Entity assignedNew = new Entity(NameKinds.AssignList, newAssigneeID, parentBookTitleKey);
				assignedNew.setUnindexedProperty(GenProp.Date, new Date());
				datastore.put(txn, assignedNew);
			}
			
		}
	}
	
	public void changeWaitAndAssignList_ForForceIssue (Entity assigned, Key parentBookTitleKey, Transaction txn) {
		
		
		
	}

	public void issueBookByCategory (String personID, String bookID ,Date todayDate) {
		
		System.out.println("\n\nNewwwwwwwwwww");
		
		TransactionOptions options = TransactionOptions.Builder.withXG(true);
	    Transaction txn = datastore.beginTransaction(options);

	    Entity person = null;
	    Entity book = null;
	    Entity categoryWiseRules = null;
	   
	    try {
	    	
	    	try {
    	    	person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
    //	    	This will not work for child entities. either we need parent entity or we need to query
	//			book = datastore.get(KeyFactory.createKey(NameKinds.Book, bookID));
    	    	Filter f = new FilterPredicate(BookProp.bookID, FilterOperator.EQUAL, bookID);
    	    	Query q = new Query(NameKinds.Book).setFilter(f);
    	    	book = datastore.prepare(q).asSingleEntity();
    	    	if (book==null)
    	    		throw new Exception();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		System.out.println("EntityNotFound BookID or PersonID is invalid");
	    		throw new TransactionFailed ();
	    	}
	    	
	    	 Entity assigned = null ;
	    	// person is not in assign list
	    	 Key parentBookTitleKey = book.getParent();
	    	 
	    	try {
	    		assigned = datastore.get(KeyFactory.createKey(parentBookTitleKey, NameKinds.AssignList, personID));
	    	} catch (Exception e) {
		    	
				if (availableBooksInTitle(parentBookTitleKey) <= numberOfAssigneeToTitle(parentBookTitleKey)) {
					System.out.println("All books fully assigned to others. not available");
					throw new TransactionFailed ();
				}
	    	}
	    	
	    	
	    	
			String personRole = (String) person.getProperty(PersonProp.role);
			String bookCategory = (String) book.getProperty(BookProp.categoryType);
			String readerType = null;
			try {
				Entity roleDefine = datastore.get(KeyFactory.createKey("RoleDefine", personRole));
				readerType = (String) roleDefine.getProperty("readerType");
						
				categoryWiseRules = datastore.get(KeyFactory.createKey(NameKinds.Rules, readerType +"-"+ bookCategory));
			} catch (Exception e) {
				e.printStackTrace();
	    		System.out.println("Rules is not set for readerType : " + readerType + " for category : " + bookCategory);
	    		throw new TransactionFailed ();
			}
			
			
			
			Long maxQuantity = (java.lang.Long) categoryWiseRules.getProperty(RulesProp.maxQuantity);
			Long dayLimit = (java.lang.Long) categoryWiseRules.getProperty(RulesProp.dayLimit);

			ArrayList <String> booksInPossesion = (ArrayList<String>) person.getProperty(PersonProp.inPossession +"-"+ bookCategory);
			
			Long overallTotalQty = null;
			
			if (booksInPossesion!= null) {
				
				if (booksInPossesion.size() >= maxQuantity ) {
					System.out.println("Maximum Limit for given category is reached");
					throw new TransactionFailed ();
				} else {
					
					
				}
			} else {
				System.out.println("books in possessions are null");
				booksInPossesion = new ArrayList <String> ();
			}
			
			
			
			try { // two reads are excess
				overallTotalQty = (java.lang.Long) person.getProperty(PersonProp.totalCategoryBksInPossesion);
				if (overallTotalQty==null)
					throw new Exception();
				Long maxTotalQuantity = (java.lang.Long) categoryWiseRules.getProperty(RulesProp.maxTotalQuantity);
				
				if ( overallTotalQty >= maxTotalQuantity) {
					System.out.println("Maximum Limit for ALL category total is reached");
					throw new TransactionFailed ();
				}
			} catch (Exception e) {
				// null pointer exception at overallTotalQty >= maxTotalQuantity due to null of any one of data
				//e.printStackTrace();
				System.out.println("Log : totalCategoryBksInPossesion property not set in person");
			}
			
			// Here we make changes in wait and assign list
			if (assigned!=null)
			 changeWaitAndAssignList (assigned, parentBookTitleKey, txn);
			
			
			booksInPossesion.add(bookID);
			

			if ( book.getProperty(BookProp.borrowedBy) != null) {
		    	System.out.println("Book is already taken");
		    	throw new TransactionFailed ();
		    }
			
			

			// now all the checks are done, now we can set properties.
			
			person.setProperty(PersonProp.inPossession+"-"+ bookCategory, booksInPossesion);
			//if (overallTotalQty!=null) {
			if (overallTotalQty == null) {
				overallTotalQty = new Long (0);
			}
			person.setProperty(PersonProp.totalCategoryBksInPossesion , overallTotalQty+1);
			//}
			
			datastore.put(txn, person);
			
		    
		    book.setProperty(BookProp.borrowedBy, personID);
		    
		    book.setProperty(BookProp.issuedType, GenProp.categoryIssue);
		    book.setProperty(BookProp.issueDate, todayDate);
		    
		    //java.lang.Long dayLimit = (Long) roleWiseRules.getProperty(RoleWiseRulesProp.dayLimitForEachBook);
		    
		    GregorianCalendar cal = new GregorianCalendar();
	        cal.setTime(todayDate);
	        
	        cal.add(Calendar.DATE, dayLimit.intValue());
	        
	        
		    
		    book.setProperty(BookProp.expectedReturnDate, cal.getTime());
		    
		    datastore.put(txn, book);
		    
		    txn.commit();
			
		    System.out.println("Book issed Success !!!!!  to " + personID + " the book " + bookID);
		    
	//	    createTransactionHistory ( person ,  book , bookID ,  personID , todayDate , GenProp.categoryIssue);
		    
	    // 8 get    	5 set
		    
	    } catch (TransactionFailed e) {
	    	e.printStackTrace();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
		      if (txn.isActive()) {
		          txn.rollback();
		      }
		}
		
		
		
	}
	
	
	
	
	
	
	
	// DECIDING ISSUETYPE PERTICULAR TO PERSON
		public void issueBookByIssueType (String personID, String bookID ,Date todayDate , String issueType) {
			
			System.out.println("\n\nNewwwwwwwwwww");
			
			TransactionOptions options = TransactionOptions.Builder.withXG(true);
		    Transaction txn = datastore.beginTransaction(options);

		    Entity person;
		    Entity book;
		    Entity issueWiseRules = null;
		    
		    try {
		    	
		    	try {
		    		person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
		//			book = datastore.get(KeyFactory.createKey(NameKinds.Book, bookID));
		    		Filter f = new FilterPredicate(BookProp.bookID, FilterOperator.EQUAL, bookID);
	    	    	Query q = new Query(NameKinds.Book).setFilter(f).setKeysOnly();
	    	    	book = datastore.prepare(q).asSingleEntity();
	    	    	if (book==null)
	    	    		throw new Exception();
		    	} catch (Exception e) {
		    		//e.printStackTrace();
		    		System.out.println("BookID or PersonID is invalid");
		    		throw new TransactionFailed ();
		    	}
		    	
		    	
		    	
		    	
		    	
		    	Entity assigned = null ;
		    	Key parentBookTitleKey = book.getParent();;
		    	try {
		    		assigned = datastore.get(KeyFactory.createKey(parentBookTitleKey, NameKinds.AssignList, personID));
		    	} catch (Exception e) {
			    	
					if (availableBooksInTitle(parentBookTitleKey) <= numberOfAssigneeToTitle(parentBookTitleKey)) {
						System.out.println("All books fully assigned to others. not available");
						throw new TransactionFailed ();
					}
		    	}
		    	
		    	
		    	
				String personRole = (String) person.getProperty(PersonProp.role);
				//String bookCategory = (String) book.getProperty("CategoryType");
				String readerType = null;
				try {
					Entity roleDefine = datastore.get(KeyFactory.createKey("RoleDefine", personRole));
					readerType = (String) roleDefine.getProperty("readerType");

					issueWiseRules = datastore.get(KeyFactory.createKey(NameKinds.Rules, readerType +"-"+ issueType));
				} catch (Exception e) {
					//e.printStackTrace();
		    		System.out.println(e.toString() + " Rules is not set for readerType : " + readerType + " for category : " + issueType);
		    		throw new TransactionFailed ();
				}
				
				Long dayLimit = (java.lang.Long) issueWiseRules.getProperty(RulesProp.dayLimit);
				// need not be always set
				Long maxQuantity = (java.lang.Long) issueWiseRules.getProperty(RulesProp.maxQuantity);

				

				if ( book.getProperty(BookProp.borrowedBy) !=null) {
			    	System.out.println("Book is already taken");
			    	throw new TransactionFailed ();
			    }

				
				if (maxQuantity != null) {
					
					ArrayList <String> booksInPossesion =  (ArrayList<String>) person.getProperty(PersonProp.inPossession+"-"+ issueType);

					if (booksInPossesion!= null) {
						if (booksInPossesion.size() >= maxQuantity ) {
							System.out.println("Maximum Limit for given issue type is reached");
							throw new TransactionFailed ();
						} 

					} else {
						System.out.println("books in possessions are null");
						booksInPossesion = new ArrayList <String> ();
					}
					
					booksInPossesion.add(bookID);
					person.setProperty(PersonProp.inPossession+"-"+ issueType, booksInPossesion);
					
				} else {
					
					// if there is no max quantity for given issue type, there is no need to store these books under 
					// seperate column. we can store them inside one combine issue list.
					
					ArrayList <String> booksInPossesion =  (ArrayList<String>) person.getProperty(PersonProp.inPossession+"-"+ "AllOther");

					if (booksInPossesion == null) {
						System.out.println("books in possessions are null");
						booksInPossesion = new ArrayList <String> ();					
					} 
					
					booksInPossesion.add(bookID);
					person.setProperty(PersonProp.inPossession+"-"+ GenProp.allOtherIssues, booksInPossesion);
//					person.setProperty("CountOfAllOther", booksInPossesion.size());
					
				}
				
				
				// Here we make changes in wait and assign list
				if (assigned!=null)
				changeWaitAndAssignList (assigned, parentBookTitleKey, txn);
				
				
				datastore.put(txn, person);
				
				
				
			    book.setProperty(BookProp.borrowedBy, personID);
			    
			    book.setProperty(BookProp.issuedType, issueType);
			    book.setProperty(BookProp.issueDate, todayDate);
			    
			    //java.lang.Long dayLimit = (Long) roleWiseRules.getProperty(RoleWiseRulesProp.dayLimitForEachBook);
			    
			    GregorianCalendar cal = new GregorianCalendar();
		        cal.setTime(todayDate);
		        
		        cal.add(Calendar.DATE, dayLimit.intValue());
		        
		        
			    
			    book.setProperty(BookProp.expectedReturnDate, cal.getTime());
			    
			    datastore.put(txn, book);
			    
			    txn.commit();
			    
			    System.out.println("Book issed Success !!!!!  to " + personID + " the book " + bookID);
			    
		//	    createTransactionHistory ( person ,  book , bookID ,  personID , todayDate , issueType);
			    
		    } catch (TransactionFailed e) {
		    	e.printStackTrace();
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } finally {
			      if (txn.isActive()) {
			          txn.rollback();
			      }
			}
			
			
		}
		
		
		
//		public void issueBookByFreeIssue (String personID, String bookID ,Date todayDate , Date returnDate , int finePerDay) {
//			
//			issueBookByFreeIssue ( personID,  bookID , todayDate ,  returnDate , finePerDay, false);
//			
//		}
		
		
		
		public void issueBookByFreeIssue (String personID, String bookID ,Date todayDate , Date returnDate , int finePerDay , boolean isForceIssue) {
			
			System.out.println("\n\nNewwwwwwwwwww");
			
			TransactionOptions options = TransactionOptions.Builder.withXG(true);
		    Transaction txn = datastore.beginTransaction(options);

		    Entity person;
		    Entity book;
		    
		    
		    try {
		    	
		    	try {
		    		person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
		//			book = datastore.get(KeyFactory.createKey(NameKinds.Book, bookID));
		    		Filter f = new FilterPredicate(BookProp.bookID, FilterOperator.EQUAL, bookID);
	    	    	Query q = new Query(NameKinds.Book).setFilter(f).setKeysOnly();
	    	    	book = datastore.prepare(q).asSingleEntity();
	    	    	if (book==null)
	    	    		throw new Exception();
		    	} catch (Exception e) {
		    		System.out.println("BookID or PersonID is invalid");
		    		throw new TransactionFailed ();
		    	}
				

				if ( book.getProperty(BookProp.borrowedBy) !=null) {
			    	System.out.println("Book is already taken");
			    	throw new TransactionFailed ();
			    }

				Entity assigned = null ;
		    	Key parentBookTitleKey = book.getParent();;
		    	try {
		    		assigned = datastore.get(KeyFactory.createKey(parentBookTitleKey, NameKinds.AssignList, personID));
		    	} catch (Exception e) {
			    	
					if (availableBooksInTitle(parentBookTitleKey) <= numberOfAssigneeToTitle(parentBookTitleKey)) {
						if (isForceIssue) {
							System.out.println("All books fully assigned to others. not available. Force issue ON.");
						} else {
							System.out.println("All books fully assigned to others. not available. Force issue OFF");
							throw new TransactionFailed ();
						}
					}
		    	}
				
				// if there is no max quantity for given issue type, there is no need to store these books under 
				// Separate column. we can store them inside one combine issue list.
				
				ArrayList <String> booksInPossesion =  (ArrayList<String>) person.getProperty(PersonProp.inPossession+"-"+ "AllOther");

				if (booksInPossesion == null) {
					System.out.println("books in possessions are null");
					booksInPossesion = new ArrayList <String> ();					
				} 
				
				booksInPossesion.add(bookID);
				person.setProperty(PersonProp.inPossession+"-"+ GenProp.allOtherIssues, booksInPossesion);
//				person.setProperty("CountOfAllOther", booksInPossesion.size());
				
				
				// Here we make changes in wait and assign list
				if (assigned!=null) {
					if (isForceIssue) {
						changeWaitAndAssignList_ForForceIssue (assigned, parentBookTitleKey, txn);
					} else {
						changeWaitAndAssignList (assigned, parentBookTitleKey, txn);
					}
				}
				
				
				datastore.put(txn, person);
				
				
				
			    book.setProperty(BookProp.borrowedBy, personID);
			    book.setProperty(BookProp.issuedType, GenProp.customTypeIssue);
			    book.setProperty(BookProp.issueDate, todayDate);
			    book.setProperty(BookProp.expectedReturnDate, returnDate);
			    
			    datastore.put(txn, book);
			    
			    txn.commit();
			    
			    System.out.println("Book issed Success !!!!!  to " + personID + " the book " + bookID);
		//	    createTransactionHistory ( person ,  book , bookID ,  personID , todayDate , GenProp.customTypeIssue);
			    
		    } catch (TransactionFailed e) {
		    	e.printStackTrace();
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } finally {
			      if (txn.isActive()) {
			          txn.rollback();
			      }
			}
			
			
		}
		
		
		
		public void setMemcashe (Object key , Object value) {
			
			// Create a MemcacheService that uses the namespace "abc".
			
			explicit.put(key, value);  // stores value in namespace "abc"
		}
		
		public Object getMemcashe (Object key) {
			
			return (Object) explicit.get(key);
		}

		
		public void allReturnOne (String bookID, Date todayDate) {
			
		    Entity book;
		    String personID;
		    Entity person;
		    
			try {
				KeyFactory.createKey(NameKinds.Book, bookID); // Experimental
		//		book = datastore.get(KeyFactory.createKey(NameKinds.Book, bookID));
				Filter f = new FilterPredicate(BookProp.bookID, FilterOperator.EQUAL, bookID);
    	    	Query q = new Query(NameKinds.Book).setFilter(f);
    	    	book = datastore.prepare(q).asSingleEntity();
    	    	if (book==null) {
    	    		throw new Exception();
    	    	}
    	    		
				personID = (String) book.getProperty(BookProp.borrowedBy);
				if (personID == null) {
					System.out.println("Book is not given to anyone");
					throw new Exception();
				}
				person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
	    	} catch (Exception e) {
	    		System.out.println("BookID or PersonID is invalid");
	    		e.printStackTrace();
	    		return;
	    	}
			
			allreturn (person, personID, book, bookID, todayDate);
			
		}
		
		public void allReturn (String personID, String bookID, Date todayDate) {
			 Entity person;
			  Entity book;
			  
			  try {
		    		person = datastore.get(KeyFactory.createKey(NameKinds.Person, personID));
		    		Filter f = new FilterPredicate(BookProp.bookID, FilterOperator.EQUAL, bookID);
	    	    	Query q = new Query(NameKinds.Book).setFilter(f);
	    	    	book = datastore.prepare(q).asSingleEntity();
	    	    	if (book==null)
	    	    		throw new Exception();
		    	} catch (Exception e) {
		    		
		    		System.out.println("BookID or PersonID is invalid");
		    		e.printStackTrace();
		    		return;
		    	}
			  
			  allreturn (person, personID, book, bookID, todayDate);
		}
		
		private void changeAssignListAfterReturn (Key parentBookTitleKey, Transaction txn) {

	    		// For dates and times, ascending means that earlier values precede later ones e.g. 1/1/2000 will sort ahead of 1/1/2001.
    	    	Query q = new Query(NameKinds.WaitList, parentBookTitleKey).addSort("Date", SortDirection.ASCENDING).setKeysOnly();
    	    	List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(1));
    			if (results.isEmpty())
    				return;
    			
    			Key waitlistedKey = results.get(0).getKey();
    			String waitListedID = waitlistedKey.getName();
    			
    	    	Entity assignList = new Entity(NameKinds.AssignList, waitListedID, parentBookTitleKey);
    	    	datastore.delete(txn, waitlistedKey);
    	    	datastore.put(txn, assignList);
    			
	    	
	    	
		}
		
		private void allreturn (Entity person, String personID, Entity book, String bookID, Date todayDate) {
			
			TransactionOptions options = TransactionOptions.Builder.withXG(true);
		    Transaction txn = datastore.beginTransaction(options);
		   
		    
		    Entity issueCatWiseRules;
		    Date issueDate = null;
		    
			try {
		    	
				
				String personRole = (String) person.getProperty(PersonProp.role);
				String issuedType = (String) book.getProperty(BookProp.issuedType);
				System.out.println("issuedType  " + issuedType);
				String bookCategoryORIssue = issuedType;
				
				// newly added
				if (issuedType == null) {
					System.out.println("Book is not taken");
					return;
				}
				
				Long overallTotalQty = null;
				if (issuedType.equals(GenProp.categoryIssue)) {
					
					bookCategoryORIssue = (String) book.getProperty(BookProp.categoryType);
					overallTotalQty = (java.lang.Long) person.getProperty(PersonProp.totalCategoryBksInPossesion);
				} 
				
				String readerType = null;
				try {
					Entity roleDefine = datastore.get(KeyFactory.createKey("RoleDefine", personRole));
					readerType = (String) roleDefine.getProperty("readerType");

					issueCatWiseRules = datastore.get(KeyFactory.createKey(NameKinds.Rules, readerType +"-"+ bookCategoryORIssue));
					System.out.println(readerType +"-"+ bookCategoryORIssue);
					
				} catch (Exception e) {
					e.printStackTrace();
		    		System.out.println("Rules is not set for readerType : " + readerType + " for category/Issue : " + bookCategoryORIssue);
		    		return;
				}
				
				// dayLimit not needed as we have expected return date and todays date
				//Long dayLimit = (java.lang.Long) issueCatWiseRules.getProperty(RulesProp.dayLimit);
				// need not be always set
				Long maxQuantity = (java.lang.Long) issueCatWiseRules.getProperty(RulesProp.maxQuantity);

		    	
				
				if (maxQuantity == null) {
					bookCategoryORIssue = GenProp.allOtherIssues;
					
				} 

				ArrayList <String> booksInPossesion =  (ArrayList<String>) person.getProperty(PersonProp.inPossession +"-"+ bookCategoryORIssue);

					
				if (booksInPossesion!= null) {
					
					if (booksInPossesion.contains(bookID) ) {
						
						booksInPossesion.remove(bookID);
						if (booksInPossesion.isEmpty()) {
							person.setProperty(PersonProp.inPossession +"-"+ bookCategoryORIssue, null);
						} else {
							person.setProperty(PersonProp.inPossession +"-"+ bookCategoryORIssue, booksInPossesion);
						}
						System.out.println("Book is removed from student.");
						
					} else {
						System.out.println("Book is not with Person ..");
						throw new TransactionFailed ();
					}
					
				} else {
					System.out.println("Book is not with Person; booksInPossesion null");
					throw new TransactionFailed ();
				}
					
				
				
				if (issuedType.equals(GenProp.categoryIssue) && overallTotalQty!=null) {
					person.setProperty(PersonProp.totalCategoryBksInPossesion , (overallTotalQty-1));
				}
				
			    datastore.put(txn, person);
			    
			    
			    // Newly Added
		    	Key parentBookTitleKey = book.getParent();
		    	changeAssignListAfterReturn (parentBookTitleKey, txn);
		    	
			    
			    issueDate = (Date) book.getProperty(BookProp.issueDate);

			    Date expectedReturnDate = (Date) book.getProperty(BookProp.expectedReturnDate);
			    
			    book.setProperty(BookProp.borrowedBy, null);
			    book.setProperty(BookProp.issuedType, null);
			    book.setProperty(BookProp.issueDate, null);
			    book.setProperty(BookProp.expectedReturnDate, null);
			    
			    datastore.put(txn, book);
			    
			   
			    float finePerDay = (float) 0.0;
			    float fine = (float) 0.0;
			    
			    try {
			    	finePerDay = (float) issueCatWiseRules.getProperty(RulesProp.finePerDay) ;
			    } catch (Exception e) {
			    	System.out.println(e.toString() + " Exception at line: fine = (float) issu..  Fine is not set");
			    }
			    
			    if (finePerDay != 0.0 ) {
			    	long diff = issueDate.getTime() - expectedReturnDate.getTime();
				   
			    	if (diff / 1000 / 60 / 60 / 24 >0) {
			    		fine = (diff / 1000 / 60 / 60 / 24)*finePerDay;
			    		System.out.println("difference day : " + diff + " fine : " + fine);
			    	}
			    }
			    

			    // message displaying fine to collect
			    float fineCollected = fine; // for now
			    
			    txn.commit();
			    
			    
			    
			    System.out.println("Book is returned. \n");
			    
			    createTransactionHistoryReturning ( person ,  book , bookID ,  personID , issueDate , 
			    		GenProp.customTypeIssue, todayDate , fineCollected);
			    
			} catch (TransactionFailed e) {
				
			} finally {
			      if (txn.isActive()) {
			          txn.rollback();
			      }
			}
			
			
		}
		
		
		
		
		public void createTransactionHistoryReturning ( Entity person , Entity book , String bookID , String personID ,
				Date issueDate , String issueType, Date todayDate , float fineCollected ) {
		 	SimpleDateFormat sdf = new SimpleDateFormat("YYMMdd");
		    Entity transactionHistory = new Entity(NameKinds.TransactionHistory , personID+bookID+sdf.format(todayDate) , person.getKey());
		    System.out.println("tran his  : " + personID+bookID+"_"+sdf.format(todayDate));
		    transactionHistory.setProperty(TransactionHistoryProp.bookID, bookID);
			transactionHistory.setProperty(TransactionHistoryProp.person, personID);
			transactionHistory.setProperty(TransactionHistoryProp.dateOfIssue, todayDate);
			transactionHistory.setProperty(TransactionHistoryProp.issuetype, issueType);
			
			transactionHistory.setProperty(TransactionHistoryProp.dateOfReturn, null);
			//transactionHistory.setProperty(TransactionHistoryProp.fineCollected, null); // saved two writes
			
			
			// renew index, transaction ID ,

			String degree = (String) person.getProperty(PersonProp.degree);
			//String branch = (String) person.getProperty(PersonProp.branch);
			String courseyear = (String) person.getProperty(PersonProp.courseyear);
			
			transactionHistory.setProperty(TransactionHistoryProp.degree, degree);
			//transactionHistory.setProperty(TransactionHistoryProp.branch, branch);
			transactionHistory.setProperty(TransactionHistoryProp.courseyear, courseyear);

			String bookName = (String) book.getProperty(BookProp.bookName);
			String authour = (String) book.getProperty(BookProp.author);

			transactionHistory.setProperty(TransactionHistoryProp.bookName, bookName);
			transactionHistory.setProperty(TransactionHistoryProp.authour, authour);

			transactionHistory.setProperty(TransactionHistoryProp.renewIndex, 1);
		    
			transactionHistory.setProperty(TransactionHistoryProp.dateOfReturn, todayDate);  // these are taken now.
			transactionHistory.setProperty(TransactionHistoryProp.fineCollected, fineCollected); // these are taken now.
				
			datastore.put(transactionHistory);
			
			System.out.println("INFO : transaction comple, book issues \n");
	}
	

}
