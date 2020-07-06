# CRUD-Using-H2-database

### Table of contents
-[Description](#description)
-[How to Use](#how-to-use)

## Description
This Java program used to perform CRUD(Create, Read, Update and Delete) operations in the H2 database. It calculates the Real Estate tax value for an year of all the properties of the individual owner.
Each property has different tax values and property-type can be house, office, land, industrial etc. 

## How to Use

#### Create
We have to create a database with all the necessary data columns (Address, Owner, Size, Market_value and Property_type) and once the connection has been established with database url, username, password and 
database driver, it is easy to create the databse.

#### Insert
Once the database has been created, we have to insert all the necessary data for the table to perform the necessary action for the user.

#### Read
Once the insertion of data in the table is finished, we can read the table with the read function defined in the code.To read the data, sql query has been used which will display all the fields in the table.

#### Update
Update of the table can be performed if there is wrong insertion of data or the data has to be altered. Update is performed for certain cloumn with the ID because it is used as a primary key in the table.

#### Delete
Table records can be deleted with the id in the table since it is used as primary key.

## Real Estate Tax Calculation
Tax records for the individual user can be calculated by obtaining user name with all the type of proprty he has and with the market value of each property.
### Tax for an year =(12*(Tax_value of the property*Market_value)/100). 
