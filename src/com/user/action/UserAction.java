package com.user.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.user.mysql.mysqlOperate;
import com.user.userbook.BOOK;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
@SuppressWarnings({ "serial", "unused" })
public class UserAction extends ActionSupport {
    private String authorNAME;
    private String publishdate;
    private String publisher;
    private String price;
    private String authorID;
    private String Title;
    private String Country;
    private String Age;

	private mysqlOperate sqlOprate=new mysqlOperate();
    private List<BOOK> bookList;
    private String ISBN;
    private Map<String,String> detailMap;

	public String queryBook() {
    	String authorNAME=getAuthorNAME();
    	List<BOOK> bList=sqlOprate.userQuery(authorNAME);
    	this.bookList=bList;
    	
    	if(bList==null||bList.size()==0){
    		return "failure";
    	}
    	else{
    	return "success";
    	}
    }
    public String deleteBook() {
    	boolean deleteResult=sqlOprate.userDelete(this.ISBN);
    	if (deleteResult) {
			return "success";
		}
        return "failure";
    }
    public String showDetail() {
    	this.detailMap=sqlOprate.showDetail(this.ISBN);
    	if(this.detailMap.isEmpty()){
    		return "failure";
    	}
        return "detailPage";
    }
    public String updateDetail() {
    	this.detailMap=sqlOprate.updateDetail(this.ISBN,this.authorID,this.publishdate,this.publisher,this.price);
        return "detailPage";
    }
    public String insertbook() {
    	this.detailMap=sqlOprate.insertbook(this.ISBN,this.authorID,this.publishdate,this.publisher,this.price,this.Title,this.authorNAME,this.Country,this.Age);
        return "detailPage";
    }
    

    public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
    public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
    public String getAuthorID() {
		return authorID;
	}
	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}
    public String getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
    public Map<String,String> getDetailMap() {
		return detailMap;
	}
	public void setDetailMap(Map<String,String> detailMap) {
		this.detailMap = detailMap;
	}

    public String getAuthorNAME() {
        return authorNAME;
    }
    public void setAuthorNAME(String authorNAME) {
        this.authorNAME = authorNAME;
    }
    public List<BOOK> getBookList() {
        return bookList;
    }
    public void setBookList(List<BOOK> bList) {
        this.bookList = bList;
    }
    public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
}