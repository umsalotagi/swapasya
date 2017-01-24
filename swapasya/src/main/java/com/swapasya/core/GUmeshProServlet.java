package com.swapasya.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.Link;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.PostalAddress;
import com.google.appengine.api.datastore.Rating;
import com.google.appengine.api.datastore.Transaction;


@SuppressWarnings("serial")
public class GUmeshProServlet extends HttpServlet {
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		DatastoreService sa = DatastoreServiceFactory.getDatastoreService();
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, Welcome to GAE datasore !!! General");
		
	//  
		// waitlist , confirmed status for a request , renew are very co relevent

		
		GeneralFinal gen = new GeneralFinal ("MH001");
		Date d = new Date ();
		
		
		
//		try {
//			createBookFromCSV(gen);
//			createperson();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		Read r5 = new Read("MH001");
		
		r5.getAssignList("16119");


		System.out.println("Exit");
		System.exit(0);
		
		try {
			gen.addMeToWaitORAssignList ( "161119" , "T_B02");
			gen.addMeToWaitORAssignList ( "161119" , "T_B02");
			gen.addMeToWaitORAssignList ( "161119" , "T_B02");
			gen.addMeToWaitORAssignList ( "161120" , "T_B02");
			gen.addMeToWaitORAssignList ( "161120" , "T_B02");
			
			
		} catch (TransactionFailed e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		gen.issueBookByCategory("161120", "B17", d);
		gen.issueBookByCategory("161119", "B15", d);
		gen.issueBookByCategory("161119", "B18", d);
		
		gen.allReturn(  "161120","B17", d);
	//	gen.allReturnOne("B15", d);
	//	gen.allReturnOne("B18", d);
		
		System.out.println("Exit");
		System.exit(0);
		
//		
		Read r3 = new Read("MH001");
		try {
			r3.getHistory("161119");
			r3.getHistory("161117");
			
			r3.getHistoryForBook("B10");
			r3.getHistoryForBook("B07");
			r3.getHistoryForBook("B13");
		} catch (TransactionFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<String> tags = new ArrayList <String> ();
		ArrayList <Category> ctags = new ArrayList <Category> ();
		Rating r2 = new Rating(3);
		Link l = new Link("path");
		gen.addBook("B27", "354667", "One2", "Two", "Book", "My", 200, d, "OK", ctags, 222, "Marathi", 1, r2, l);
		gen.addBook("B28", "354665", "One2", "Two", "Book", "My", 200, d, "OK", ctags, 222, "Marathi", 1, r2, l);
		gen.addBook("B29", "354665", "One2", "Two", "Book", "My", 200, d, "OK", ctags, 222, "Marathi", 1, r2, l);
		
		
		System.out.println("Exit");
		System.exit(0);
		
	//	TestCase(gen , d);
// 		gen.issueBookByCategory("161120", "B01", d);
//		
//		gen.issueBookByIssueType("161120", "B02", d, "BookBank");
//		gen.issueBookByCategory("161120", "B03", d);
//		gen.issueBookByCategory("161118", "B04", d);
//		gen.issueBookByCategory("161118", "B05", d);
//		
//		System.out.println("book returningggggggg");
//		
//		gen.allreturn("161120", "B01", d);
//		gen.allreturn("161120", "B02", d);
//		gen.allreturn("161120", "B03", d);
//		gen.allreturn("161118", "B04", d);
//		gen.allreturn("161118", "B05", d);
		
		System.out.println("Get History ::::::::::::::::::::");
		Read r = new Read("MH001");
		try {
			r.getHistory("161119");
		} catch (TransactionFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("\n\n\n\n Return");
		
	//	testCase( gen,  d , 30);
	}
	

	
	String[] bookss = {"B01","B02", "B03","B04", "B05","B06","B07","B08","B09","B10", "B11","B12", "B13","B14", "B15",
			"B16","B17","B18","B19","B20", "C01","C02", "C03","C04", "C05","M01","M02", "M03","M04", "M05",};
	
	// 8
	String[] studentt = { "161117" , "161118" ,"161119", "161120", "161121" , "161122", "161123" , "161124"};
	
	String[] issueType = { "BookBank", "GATE" , "Topper"};
	
	public void testCase(GeneralFinal gen, Date d , int noOfSteps) {
		
		// Create 3 category, 2 roles , 2 issue , freeissue,
		
		//String personRole, String bookCategory, int dayLimitForEachBook,  int bookQuantityLimit
		//students , books ..
		ArrayList <Integer> st = new ArrayList <Integer>();
		ArrayList <Integer> bk = new ArrayList <Integer>();
		
		
		
		
		for (int i=0; i<noOfSteps; i++) {
			//int issueTypeTrue = (int) getRandomInt (0, 2);
			int issueTypeTrue = 1;
			int issueTypeIndex = (int) getRandomInt (0, 3);
			System.out.println("index " + issueTypeTrue + " " + issueType[issueTypeIndex]);
			int S1 = (int) getRandomInt (0, 8);
			int B2 = (int) getRandomInt (0, 15);
			st.add(S1);
			bk.add(B2);
			
			gen.issueBookByCategory(studentt[S1], bookss[B2], d);
			
			if (issueTypeTrue==1) {
				gen.issueBookByIssueType(studentt[S1], bookss[B2], d, issueType[issueTypeIndex]);
				System.out.println(bookss[B2] + " to " +  studentt[S1]+" " + issueType[issueTypeIndex] + " issue type issued");
			} else {
				System.out.println(bookss[B2] + " to " +  studentt[S1]+ " issued");
			}
	
			sleep(50);
			
			
		}
		
		
		for (int i=0; i<st.size(); i++) {
			
			int k1 = st.get(i);
			int k2 = bk.get(i);
			
			gen.allReturn(studentt[k1], bookss[k2], d);
			
			System.out.println(bookss[k2] + " return " +  studentt[k1]+ " ");
		}
		
	}
	
	public void sleep (int mi) {
		try {
			Thread.sleep(mi);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public long getRandomInt ( int minimum , int maximum) {
		  return (minimum + (int)(Math.random() * maximum)); 
	  }
	
	
	public void createperson() {
		
		
		createPerson("161117", "Sagar", "Gag", "Teacher", "Shivann col, nagar", "757575757");
		createPerson("161118", "AMit", "Paul", "Teacher", "Jaature , solapur", "655555555");
		createPerson("161119", "Rahul", "Kana", "Student", "bharat nagar, solapur", "77777777777");
		createPerson("161120"	, "Ram", "Sur", "Student", " Ram Nagar , New Delhi", "9908997688");
		createPerson("161121"	, "Shyam", "Pathak", "Student", " Vishal Nagar , New Delhi", "5555997688");
		createPerson("161122"	, "Parshya", "DIsh", "Student", " Zinagt Nagar , solapur", "111111111");
		createPerson("161123"	, "Swati", "Gajare", "Student", " Shivshahi Nagar , Solapur", "2222222222");
		createPerson("161124"	, "Avanti", "Surwase", "Student", " Shivaji Nagar , Mumbai", "6666666666");
	
	}
	
	public void createPerson (String ID , String firstName ,  String lastName, String role ,String addd , String phh) {
		
		General gen = new General ("MH001");
		PostalAddress add = new PostalAddress (addd);
		PhoneNumber ph = new PhoneNumber (phh);
		
		gen.addPersonDetailsTemp(ID, "123", firstName, lastName, add, ph, role);
		
	//	gen.addPersonDetails(ID, password, firstName, lastName, gUser, address, emailId, mobileNo, contactNo, degree, branch, courseyear, division, rollNo, booksInPossesion, isBookBankEnabled, bookBankID, role, admissionDate);
		
	}
	
	ArrayList <ArrayList <String>> textDb;
	
	// wehn there is new file add it to path :
	//it has to add to :
		//      GUmeshPro\war\WEB-INF\classes\   your file
	
	public void createBookFromCSV (GeneralFinal gen) throws ClassNotFoundException {
		
		textDb = new ReadTxt ().readT ("\\TB.csv");
		textDb.get(0).get(0);
		
		StringBuffer s = new StringBuffer();
		
		for (ArrayList <String> row : textDb) {
			
			
			String bookID = row.get(0);
			if (bookID.equals("BookID")) {
				continue;
			}
			s.append("\"" + bookID + "\", ");
			
			//System.out.println(row);
			String clgName = row.get(1);
			String isbn = row.get(2);
			String bookName = row.get(3);
			String authour = row.get(4);
			String publication = row.get(5);
			String price = row.get(6);
			
			String category = row.get(8);
			String noOfPages = row.get(14);
			String language = row.get(15);
			
//			String tag1 = row.get(17);
//			String tag2 = row.get(18);
//			String tag3 = row.get(19);
//			String tag4 = row.get(20);
			
			// List<Entity> employees = Arrays.asList(employee1, employee2, employee3);
			List<String> tags = new ArrayList <String> ();
			ArrayList <Category> ctags = new ArrayList <Category> ();
			for (int i=17 ; i>10; i++) {
				try {
					ctags.add(new Category(row.get(i)));
				} catch (IndexOutOfBoundsException e) {
					System.out.println("indexxx");
					break;
				}

			}
			
			if (price.isEmpty() || price == "") {
				price = "0";
			}
			
			if (noOfPages.isEmpty() || noOfPages.equals("")) {
				noOfPages = "0";
			}
			
			System.out.println(bookID +" " + category + " " + noOfPages + " " + language );
			
			gen.addBook(bookID, isbn, bookName, authour, category, publication,  Integer.parseInt(price) , null, null, ctags, Integer.parseInt(noOfPages), language, 0, null, null);
			//gen.addBookTemp(bookID, bookName, authour, tags, categoryType);
		}
		
	//	System.out.println(s.toString());
		
	}
	
	public void setRules (GeneralFinal gen) {
		gen.roleDefine("Student", "Study");
		gen.roleDefine("Teacher", "Teach");
		
		gen.categoryWiseRules("Study", "Book",  2, 7, 4, 5);
		gen.categoryWiseRules("Study", "Magazine",  1, 4, 4, 2);
		gen.categoryWiseRules("Study", "CD",  1, 4, 4, 2);
		
		gen.categoryWiseRules("Teach", "Book",  4, 7, 6, 0);
		gen.categoryWiseRules("Teach", "Magazine",  2, 7, 6, 0);
		gen.categoryWiseRules("Teach", "CD",  2, 7, 6, 0);
		
		gen.issueWiseRules("Study", "BookBank",  2, 7, (float) 1.0);
		gen.issueWiseRules("Study", "GATE",  2, 7, (float) 1.0);
	}
		

	
	
}
