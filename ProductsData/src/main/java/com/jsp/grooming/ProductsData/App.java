package com.jsp.grooming.ProductsData;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("press\n1 to addProduct\n2 to find prooduuct by id\n3 to find product by name\n4 to find product between price\n5 to find product by category\n6 to update produt statu\n7 to update product price by name \n8 Delete Product by Id");
    	int ch = sc.nextInt();
       ProductServices ps = new ProductServices();
       switch(ch)
       {
       case 1:ps.addProduct();
       break;
       case 2: ps.findProductById();
       break;
       case 3: ps.findProductByName();
       break;
       case 4: ps.findProductBetweenPrice();
       break;
       case 5:ps.findProductByCategory();
       break;
       case 6:ps.updateProductStatus();
       break;
       case 7: ps.updateProductPriceByName();
       break;
       case 8:ps.deleteProductById();
       break;
       
       default:System.out.println("Provide value between 1- 8 ");
       }
      
    }
}
	