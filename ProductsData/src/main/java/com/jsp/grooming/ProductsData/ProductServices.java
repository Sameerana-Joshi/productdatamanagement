package com.jsp.grooming.ProductsData;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProductServices {
	
	Scanner sc  = new Scanner(System.in);
	
	public  void addProduct()
	{
		System.out.println("Enter the productId");
		int productId = sc.nextInt();
		
		System.out.println("Enter the productName");
		String pName = sc.next();
		
		System.out.println("Enter the productBrand");
		String pBrand = sc.next();
		
		System.out.println("Enter the productCategory");
		String pCategory = sc.next();
		
		System.out.println("Enter the productPrice");
		int pPrice = sc.nextInt();
		
		System.out.println("Enter the productStatus");
		String pStatus = sc.next();
		
		Product product = new Product();
		product.setProductId(productId);
		product.setProductName(pName);
		product.setProductPrice(pPrice);
		product.setProductStatus(pStatus);
		product.setProductCategory(pCategory);
		product.setProductBrand(pBrand);
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sameerna");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(product);
		transaction.commit();
		em.close();
	}
	
	public void findProductById()
	{
		System.out.println("Enter the productId");
		int productId = sc.nextInt();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sameerna");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Product find = em.find(Product.class, productId);
		System.out.println(find);
		transaction.commit();
		em.close();
		
	}
	
	public void findProductByName()
	{
		System.out.println("Enter the productName");
		String pName = sc.next();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sameerna");
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		  Query createQuery = em.createQuery(" FROM Product WHERE productName =:pName");
		 createQuery.setParameter("pName",pName);
		    List<Product> resultList = createQuery.getResultList();
		    for(Product ans :resultList)
		    {
		    	System.out.print(ans+" ");
		    }
		t.commit();
		em.close();
	}
	
	public void findProductBetweenPrice()
	{
		System.out.println("Enter the LowerBond");
		int lb = sc.nextInt();
		
		System.out.println("Enter the UpperBond");
		int ub = sc.nextInt();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sameerna");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		String qry ="select * from product where productprice between :lb and :ub";
						Query cnq = em.createNativeQuery(qry,Product.class);
					cnq.setParameter("lb", lb);
					cnq.setParameter("ub", ub);
		List<Product> resultList = cnq.getResultList();
		for(Product ans :resultList)
		{
			
				System.out.println(ans);
			
		}
		et.commit();
		em.close();
		
	}
	
	public void findProductByCategory()
	{
		System.out.println("Enter the category");
		String cat = sc.next();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sameerna");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		String qry ="select * from Product where productCategory =:pCat";
	
	    Query cnq = em.createNativeQuery(qry,Product.class);
    	cnq.setParameter("pCat", cat);
					
		List<Product> resultList = cnq.getResultList();
		for(Product ans :resultList)
		{
			
			
				System.out.println(ans);
			
		}
		et.commit();
		em.close();
		
		
	}
public void updateProductStatus()
{
	String temp = "available", temp2="notavailable";
	System.out.println("Enter the productId");
	int pId = sc.nextInt();
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sameerna");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et =em.getTransaction();
	et.begin();
	Query cq = em.createQuery("SELECT productStatus FROM Product WHERE productId =:pId ");
	cq.setParameter("pId", pId);
	String status =(String) cq.getSingleResult();
	if(status.equals(temp))
	{
		Query cq2 = em.createQuery("UPDATE Product set productStatus =:temp3  WHERE productId = :pId");
		cq2.setParameter("temp3", temp2);
		cq2.setParameter("pId", pId);
		int eu = cq2.executeUpdate();
		if(eu==1)
		{
			System.out.println("Update Successfull");
		}
		else
		{
			System.out.println("Status has  already been updated");
		}
			
		
	}
	else
	{
		System.out.println("Status has  already been updated");	
	}
	
	et.commit();
	em.close();
	
}

public void updateProductPriceByName()
{
	System.out.println("Enter the productName");
	String pName = sc.nextLine();
	System.out.println("Enter the updated Price");
	int up = sc.nextInt();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sameerna");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	et.begin();
	Query cq = em.createQuery("UPDATE Product set productPrice=:updatedPrice where productName =:pName");
	cq.setParameter("updatedPrice", up);
	cq.setParameter("pName", pName);
	int eu = cq.executeUpdate();
	if(eu==1)
	{
		System.out.println("Update Successfull");
	}
	else
	{
		System.out.println("Update Failed");
	}
	
	et.commit();
	em.close();
}

public void deleteProductById()
{
	System.out.println("Enter the id of the brand");
	int productId = sc.nextInt();
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sameerna");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	transaction.begin();
	Query cq = em.createQuery("DELETE FROM Product where productId =:pId");
	cq.setParameter("pId", productId);
	int executeUpdate = cq.executeUpdate();
	if(executeUpdate==1)
	{
		System.out.println("Delete Done");
	}

	transaction.commit();
	em.close();
}

}