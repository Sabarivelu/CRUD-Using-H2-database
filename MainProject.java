package taxJava;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;  

public class MainProject { 

   static final String JDBC_DRIVER = "org.h2.Driver";   
   static final String DB_URL = "jdbc:h2:~/db/incometa9";  
 
   static final String USER = "sabari"; 
   static final String PASS = ""; 
  
   public static void main(String[] args) { 
      boolean set=true;
      while(set)
      {
      Scanner scanner = new Scanner(System.in);
      System.out.println("1. Create"+"\n"+"2. Insert"+"\n"+"3. Update"+"\n"+"4. Delete"+"\n"+"5. Read"+"\n"+"6. Exit" );
      int choice = scanner.nextInt();
      switch(choice)
      {
      case 1: create();
      		  set=true;
      		  break;
      
      case 2: insert();
      		  set=true;
      		  break;
      		  
      case 3: update();
      		  set=true;
      		  break;
      		  
      case 4: delete();
      		  set=true;
      		  break;
      		  
      case 5: read();
      		  set=true;
      		  break;
              
      default: set=false; 
      		   System.out.println("Exiting!!...");
    	       break;      
      }
      }
      System.out.println("\t"+"Real Estate Tax Value for Each Owner");
      System.out.println("\t"+"....................................");
      taxReturnsCalculation();
      
   }
   
   public static void create()
   {
	   Connection conn = null; 
	   Statement stmt = null; 
	   try { 
	         Class.forName(JDBC_DRIVER); 

	         System.out.println("Connecting to database..."); 
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  

	         System.out.println("Creating table in given database..."); 
	         stmt = conn.createStatement(); 
	         String sql =  "CREATE TABLE TAXRETURNS " + 
	            "(Id INTEGER not NULL, " + 
	            " Address VARCHAR(255), " +  
	            " Owner VARCHAR(255), " +  
	            " Size INTEGER, " +  
	            " MarketValue INTEGER, " +
	            " PropertyType VARCHAR(255), "+
	            " PRIMARY KEY ( Id ))";
	         
	         stmt.executeUpdate(sql);
	         System.out.println("Created table in given database..."); 
	         
	         stmt.close(); 
	         conn.close(); 
	      } catch(SQLException se) { 
	         se.printStackTrace(); 
	      } catch(Exception e) { 
	         e.printStackTrace(); 
	      } finally { 
	         try{ 
	            if(stmt!=null) stmt.close(); 
	         } catch(SQLException se2) { 
	         }  
	         try { 
	            if(conn!=null) conn.close(); 
	         } catch(SQLException se){ 
	            se.printStackTrace(); 
	         } 
	      }
   }
   public static void insert()
   {
	   Connection conn = null; 
	   Statement stmt = null; 
	      try { 
	         Class.forName(JDBC_DRIVER); 
	                  
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	        
	         stmt = conn.createStatement(); 
	         Scanner scanner = new Scanner(System.in);
	  	  	 System.out.print("Enter total number of data to be inserted: ");
	  	  	 int n=scanner.nextInt();
	  	  	 for(int i=0;i<n;i++)
	  	  	 {
	  	  	   System.out.println("Enter ID:");
	  	  	   int id=scanner.nextInt();
	  	  	   scanner.nextLine();
	  		   System.out.println("Enter Address: ");
	  		   String address=scanner.nextLine();
	  		   System.out.println("Enter Owner Name: ");
	  		   String owner=scanner.nextLine();
	  		   System.out.println("Enter total size(Sqt.meters): ");
	  		   double size=scanner.nextDouble();
	  		   System.out.println("Enter Market values in euros: ");
	  		   double market_value=scanner.nextDouble();
	  		   scanner.nextLine();
	  		   System.out.println("Enter property type: ");
	  		   String property_type=scanner.nextLine();
	  		   
	  		   PreparedStatement ps = conn.prepareStatement( "INSERT INTO TAXRETURNS " + "(Id,Address,Owner,Size,MarketValue,PropertyType)"+"VALUES (?,?,?,?,?,?)");
	  		   ps.setInt(1, id);
	  		   ps.setString(2, address);
	  		   ps.setString(3, owner);
	  		   ps.setDouble(4, size);
	  		   ps.setDouble(5, market_value);
	  		   ps.setString(6, property_type);
	  	       ps.executeUpdate();
	     
	      }
	  	   stmt.close(); 
	       conn.close(); 
	      }
	  	  	 catch(SQLException se) { 
	         se.printStackTrace(); 
	      } catch(Exception e) { 
	         e.printStackTrace(); 
	      } finally { 
	         try{ 
	            if(stmt!=null) stmt.close(); 
	         } catch(SQLException se2) { 
	         } 
	         try { 
	            if(conn!=null) conn.close(); 
	         } catch(SQLException se){ 
	            se.printStackTrace(); 
	         } 
	      }
	   
		   }
   public static void read()
   {
	   
   Connection conn = null; 
   Statement stmt = null; 
   try { 
      Class.forName(JDBC_DRIVER); 
      conn = DriverManager.getConnection(DB_URL,USER,PASS);  
      
      stmt = conn.createStatement(); 
      String sql = "SELECT id, address, owner, size, marketvalue, propertytype FROM TAXRETURNS"; 
      ResultSet rs = stmt.executeQuery(sql); 
      while(rs.next()) { 
         int id  = rs.getInt("id"); 
         String address = rs.getString("address"); 
         String owner = rs.getString("owner");
         double size=rs.getDouble("size");
         double marketvalue=rs.getDouble("marketvalue");
         String propertytype = rs.getString("propertytype"); 
         
         System.out.print("ID: " + id); 
         System.out.print(", Address: " +address); 
         System.out.print(", Owner: " + owner); 
         System.out.print(", Size: " + size);
         System.out.print(", Market_Value: " + marketvalue);
         System.out.println(", Property_Type: " + propertytype);
      } 
      rs.close(); 
   } catch(SQLException se) { 
      se.printStackTrace(); 
   } catch(Exception e) { 
      e.printStackTrace(); 
   } finally { 
      try { 
         if(stmt!=null) stmt.close();  
      } catch(SQLException se2) { 
      } 
      try { 
         if(conn!=null) conn.close(); 
      } catch(SQLException se) { 
         se.printStackTrace(); 
      } 
   }  
}
   
public static void delete()
{
	Connection conn = null; 
    Statement stmt = null; 
    try {
       Class.forName(JDBC_DRIVER);  

       conn = DriverManager.getConnection(DB_URL,USER,PASS);  
 
       stmt = conn.createStatement(); 
       Scanner scanner=new Scanner(System.in);
       System.out.print("Enter id for deleting: ");
       int m=scanner.nextInt();
       String sql = "DELETE FROM TAXRETURNS " + "WHERE id = "+m; 
       stmt.executeUpdate(sql);  
       sql = "SELECT id, address, owner, size, marketvalue, propertytype FROM TAXRETURNS"; 
       ResultSet rs = stmt.executeQuery(sql);  
       
       while(rs.next())
       { 
    	   int id  = rs.getInt("id"); 
           String address = rs.getString("address"); 
           String owner = rs.getString("owner");
           double size=rs.getDouble("size");
           double marketvalue=rs.getDouble("marketvalue");
           String propertytype = rs.getString("propertytype");  
           
           System.out.print("ID: " + id); 
           System.out.print(", Address: " +address); 
           System.out.print(", Owner: " + owner); 
           System.out.print(", Size: " + size);
           System.out.print(", Market_Value: " + marketvalue);
           System.out.println(", Property_Type: " + propertytype);
       } 
       rs.close(); 
    } catch(SQLException se) { 
       se.printStackTrace();  
    } catch(Exception e) {
       e.printStackTrace(); 
    } finally {
       try { 
          if(stmt!=null) stmt.close(); 
       } catch(SQLException se2) { 
       }
       try { 
          if(conn!=null) conn.close(); 
       } catch(SQLException se) { 
          se.printStackTrace(); 
       } 
    } 
 } 
public static void update()
{
	Connection conn = null; 
    Statement stmt = null; 
    try {
       Class.forName(JDBC_DRIVER);  

       conn = DriverManager.getConnection(DB_URL,USER,PASS);  
 
       stmt = conn.createStatement(); 
       Scanner scanner=new Scanner(System.in);
       System.out.print("Enter total no. of updating in the table:");
       int n=scanner.nextInt();
       for(int i=0;i<n;i++)
       {
       System.out.print("Enter column_no for updating: "+"\n"+"1.Address"+"\n"+"2.Owner"+"\n"+"3.Size"+"\n"+"4.Market_Value"+"\n"+"5.Property_Type");
       int m=scanner.nextInt();
       scanner.nextLine();
       if(m==1)
       {
    	   System.out.println("Enter New Address for updating the table:");
    	   String address_update=scanner.nextLine();
    	   System.out.println("Enter ID for updating the table:");
    	   int s=scanner.nextInt();
    	   PreparedStatement ps = conn.prepareStatement( "UPDATE TAXRETURNS SET ADDRESS=? WHERE ID=?");
    	   ps.setString(1,address_update);
    	   ps.setInt(2, s);
    	   ps.executeUpdate();
       }
       if(m==2)
       {
    	   System.out.println("Enter New Owner for updating the table:");
    	   String owner_update=scanner.nextLine();
    	   System.out.println("Enter ID for updating the table:");
    	   int s=scanner.nextInt();
    	   PreparedStatement ps = conn.prepareStatement( "UPDATE TAXRETURNS SET OWNER=? WHERE ID=?");
    	   ps.setString(1,owner_update);
    	   ps.setInt(2, s);
    	   ps.executeUpdate(); 
       }
       if(m==3)
       {
    	   System.out.println("Enter New Size for updating the table:");
    	   double size_update=scanner.nextDouble();
    	   System.out.println("Enter ID for updating the table:");
    	   int s=scanner.nextInt();
    	   PreparedStatement ps = conn.prepareStatement( "UPDATE TAXRETURNS SET SIZE=? WHERE ID=?");
    	   ps.setDouble(1,size_update);
    	   ps.setInt(2, s);
    	   ps.executeUpdate();
       }
       if(m==4)
       {
    	   System.out.println("Enter New Market value for updating the table:");
    	   double marketvalue_update=scanner.nextDouble();
    	   System.out.println("Enter ID for updating the table:");
    	   int s=scanner.nextInt();
    	   PreparedStatement ps = conn.prepareStatement( "UPDATE TAXRETURNS SET MARKETVALUE=? WHERE ID=?");
    	   ps.setDouble(1,marketvalue_update);
    	   ps.setInt(2, s);
    	   ps.executeUpdate();
       }
       if(m==5)
       {
    	   System.out.println("Enter New Property_type for updating the table:");
    	   String propertytype_update=scanner.nextLine();
    	   System.out.println("Enter ID for updating the table:");
    	   int s=scanner.nextInt();
    	   PreparedStatement ps = conn.prepareStatement( "UPDATE TAXRETURNS SET PROPERTYTYPE=? WHERE ID=?");
    	   ps.setString(1,propertytype_update);
    	   ps.setInt(2, s);
    	   ps.executeUpdate(); 
       }
       }
    } catch(SQLException se) { 
       se.printStackTrace();  
    } catch(Exception e) {
       e.printStackTrace(); 
    } finally {
       try { 
          if(stmt!=null) stmt.close(); 
       } catch(SQLException se2) { 
       }
       try { 
          if(conn!=null) conn.close(); 
       } catch(SQLException se) { 
          se.printStackTrace(); 
       } 
    } 
 }

public static void taxReturnsCalculation()
{
	Connection conn = null; 
    Statement stmt = null; 
    try {
       Class.forName(JDBC_DRIVER);  

       conn = DriverManager.getConnection(DB_URL,USER,PASS);  
 
       stmt = conn.createStatement(); 
       
       String sql=  "SELECT id, address, owner, size, marketvalue, propertytype FROM TAXRETURNS";
       
       PreparedStatement pst = conn.prepareStatement(sql);
       ResultSet rs = pst.executeQuery();
       List<String> owner_list=new ArrayList<>();
       List<String> propertytype1_list=new ArrayList<>();
       while (rs.next()) 
       {
           owner_list.add(rs.getString("owner"));
           propertytype1_list.add(rs.getString("propertytype"));
           
           }
       for(int i=0,l=propertytype1_list.size();i<l;++i)
       {
         propertytype1_list.set(i, propertytype1_list.get(i).toLowerCase());
       }
       		
       //List<String> newpropertyList = propertytype1_list.stream()
               //.distinct() 
               //.collect(Collectors.toList()); 
       
       //System.out.println(newpropertyList);
       
       for(int i=0,l=owner_list.size();i<l;++i)
       {
         owner_list.set(i, owner_list.get(i).toLowerCase());
       }
       		
       List<String> ownerList = owner_list.stream()
               .distinct() 
               .collect(Collectors.toList()); 
       
       //System.out.println(ownerList);
   
       for(int i=0;i<ownerList.size();i++)
       {
    	   //System.out.print("sample");
    	   double sum=0;
    	   String sql1=  "SELECT owner,propertytype,marketvalue FROM TAXRETURNS";
           PreparedStatement pst1 = conn.prepareStatement(sql1);
           ResultSet rs1 = pst1.executeQuery();
    	   while(rs1.next())
    	   {
    		   if(ownerList.get(i).equalsIgnoreCase(rs1.getString("owner")))
    		   {
    			   if(rs1.getString("propertytype").equalsIgnoreCase("house"))
    			   {
    				    sum=sum+12*(15*rs1.getDouble("marketvalue")/100);    				    
    			   }
    			   if(rs1.getString("propertytype").equalsIgnoreCase("apartment"))
    			   {
    				    sum=sum+12*(20*rs1.getDouble("marketvalue")/100);  
    			   }
    			   if(rs1.getString("propertytype").equalsIgnoreCase("land"))
    			   {
   				    sum=sum+12*(25*rs1.getDouble("marketvalue")/100);  				    
    			   }
    			   if(rs1.getString("propertytype").equalsIgnoreCase("office"))
    			   {
    				    sum=sum+12*(18*rs1.getDouble("marketvalue")/100);    				    
    			   }
    			   if(rs1.getString("propertytype").equalsIgnoreCase("industrial"))
    			   {
    				    sum=sum+12*(24*rs1.getDouble("marketvalue")/100);   				    
    			   }
    			   
    		   }
    		       		   
    	   }
    	   System.out.println("Total tax value of "+ownerList.get(i)+" is " + sum);
    	   //rs.close();
       }
       
    }catch(SQLException se) { 
        se.printStackTrace();  
     } catch(Exception e) {
        e.printStackTrace(); 
     } finally {
        try { 
           if(stmt!=null) stmt.close(); 
        } catch(SQLException se2) { 
        }
        try { 
           if(conn!=null) conn.close(); 
        } catch(SQLException se) { 
           se.printStackTrace(); 
        } 
     } 

}
}


