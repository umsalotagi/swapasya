package com.swapasya.dataTypes;

public class BookProp {
	
	// NOTES
	//  waitlist, no. of books of same title.logic is yet to be decided
	//  same book is not allowed to return and again take away in same day by same person.
	
	public static final String bookID = "bookID";   //  ***Mand
	
	public static final String purchaseDate = "purchaseDate";
	public static final String price = "price";
	public static final String author= "author";
	
	public static final String bookName= "bookName";
	public static final String borrowedBy = "borrowedBy"; //(personid)
	public static final String issuedType = "issuedType"; // (e.g bookbank/normalissue?)(to be decided at run time for return date)
	public static final String categoryType = "CategoryType"; // (e.g CD/book/magazine)decided while storing book   ***Mand
	public static final String issueDate = "issueDate";
	public static final String expectedReturnDate = "expectedReturnDate";
	
	public static final String rating = "rating";
	

}
