package com.webservice.demo.config;

import com.webservice.demo.model.CategoryModel;
import com.webservice.demo.model.ProductModel;
import com.webservice.demo.model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database( ) {
    }

    public static Database CreateInstance( ) {
        if ( instance == null ) {
            instance = new Database( );
        }
        return instance;
    }

    public void EstablishConnection( ) {
        try {
            String url = "jdbc:mysql://expadb.cmjl77lme6pi.eu-west-1.rds.amazonaws.com:3306/CampDatabase";
            String user = "admin";
            String password = "b1befa207b5f1995";
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            this.connection = DriverManager.getConnection( url, user, password );
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
    }

    public List< UserModel > GetUsers( ) {
        String query = "SELECT fullName, eMail, password FROM Users WHERE isActive = 1";
        try {
                EstablishConnection( );
                Statement statement = connection.createStatement( );
                ResultSet resultSet = statement.executeQuery( query );
                List< UserModel > userList = new ArrayList<>( );
                while ( resultSet.next( ) ) {
                    UserModel userModel = new UserModel( );
                    userModel.setFullName( resultSet.getString( 1 ) );
                    userModel.seteMail( resultSet.getString( 2 ) );
                    userModel.setPassword( resultSet.getString( 3 ) );
                    userList.add( userModel );
                }
                resultSet.close( );
                return userList;
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return null;
    }
    public List< UserModel > GetUserData( int userID ) {
        String query = "SELECT fullName, eMail, password FROM Users WHERE id = ? AND isActive = 1";
        try {
            if ( connection != null ) {
                EstablishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setInt( 1, userID );
                ResultSet resultSet = preparedStatement.executeQuery( );
                List< UserModel > userList = new ArrayList<>( );
                while ( resultSet.next( ) ) {
                    UserModel userModel = new UserModel( );
                    userModel.setFullName( resultSet.getString( 1 ) );
                    userModel.seteMail( resultSet.getString( 2 ) );
                    userModel.setPassword( resultSet.getString( 3 ) );
                    userList.add( userModel );
                }
                resultSet.close( );
                return userList;
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return null;
    }

    public List< ProductModel > GetProducts( ) {
        String query = "SELECT name, categoryId, price, imageUrl FROM Products WHERE isActive = 1";
        try {
            if ( connection != null ) {
                EstablishConnection();
                Statement statement = connection.createStatement( );
                ResultSet resultSet = statement.executeQuery( query );
                List< ProductModel > productList = new ArrayList<>( );
                while ( resultSet.next( ) ) {
                    ProductModel productModel = new ProductModel( );
                    productModel.setName( resultSet.getString( 1 ) );
                    productModel.setCategoryId( resultSet.getInt( 2 ) );
                    productModel.setPrice( resultSet.getDouble( 3 ) );
                    productModel.setImageUrl( resultSet.getString( 4 ) );
                    productList.add( productModel );
                }
                resultSet.close( );
                return productList;
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return null;
    }
    public List< ProductModel > GetProductDataFromId( int productID ) {
        String query = "SELECT name, categoryId, price, imageUrl FROM Products WHERE id = ? AND isActive = 1";
        try {
            if ( connection != null ) {
                EstablishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setInt( 1, productID );
                ResultSet resultSet = preparedStatement.executeQuery( );
                List< ProductModel > productList = new ArrayList<>( );
                while ( resultSet.next( ) ) {
                    ProductModel productModel = new ProductModel( );
                    productModel.setName( resultSet.getString( 1 ) );
                    productModel.setCategoryId( resultSet.getInt( 2 ) );
                    productModel.setPrice( resultSet.getDouble( 3 ) );
                    productModel.setImageUrl( resultSet.getString( 4 ) );
                    productList.add( productModel );
                }
                resultSet.close( );
                return productList;
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return null;
    }
    public List< ProductModel > GetProductDataFromCategoryId( int categoryID ) {
        String query = "SELECT name, categoryId, price, imageUrl FROM Products WHERE categoryId = ? AND isActive = 1";
        try {
            if ( connection != null ) {
                EstablishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setInt( 1, categoryID );
                ResultSet resultSet = preparedStatement.executeQuery( );
                List< ProductModel > productList = new ArrayList<>( );
                while ( resultSet.next( ) ) {
                    ProductModel productModel = new ProductModel( );
                    productModel.setName( resultSet.getString( 1 ) );
                    productModel.setCategoryId( resultSet.getInt( 2 ) );
                    productModel.setPrice( resultSet.getDouble( 3 ) );
                    productModel.setImageUrl( resultSet.getString( 4 ) );
                    productList.add( productModel );
                }
                resultSet.close( );
                return productList;
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return null;
    }

    public List< CategoryModel > GetCategories( ) {
        String query = "SELECT id, name FROM Category WHERE isActive = 1";
        try {
            if ( connection != null ) {
                EstablishConnection();
                Statement statement = connection.createStatement( );
                ResultSet resultSet = statement.executeQuery( query );
                List< CategoryModel > categoryList = new ArrayList<>( );
                while ( resultSet.next( ) ) {
                    CategoryModel categoryModel = new CategoryModel( );
                    categoryModel.setId( resultSet.getInt( 1 ) );
                    categoryModel.setName( resultSet.getString( 2 ) );
                    categoryList.add( categoryModel );
                }
                resultSet.close( );
                return categoryList;
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return null;
    }

    public boolean AddUserData( UserModel model ) {
        String query = "INSERT INTO Users ( fullName, eMail, password ) VALUES (?,?,?)";
        try {
            if ( connection != null ) {
                EstablishConnection( );
                PreparedStatement preparedStatement = this.connection.prepareStatement( query );
                preparedStatement.setString( 1, model.getFullName( ) );
                preparedStatement.setString( 2, model.geteMail( ) );
                preparedStatement.setString( 3, model.getPassword( ) );
                return preparedStatement.execute( );
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return false;
    }

    public boolean AddProductData( ProductModel model ) {
        String query = "INSERT INTO Products (name, categoryId, imageURL,price) VALUES (?, ?, ?,?)";
        try {
            if ( connection != null ) {
                EstablishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setString( 1, model.getName( ) );
                preparedStatement.setLong( 2, model.getCategoryId( ) );
                preparedStatement.setString( 3, model.getImageUrl( ) );
                preparedStatement.setDouble( 4, model.getPrice( ) );
                return preparedStatement.execute( );
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return false;
    }

    public boolean AddCategoryData( CategoryModel model ) {
        String query = "INSERT INTO Category (name) VALUES (?)";
        try {
            if ( connection != null ) {
                EstablishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setString( 1, model.getName( ) );
                return preparedStatement.execute( );
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return false;
    }

    public int UpdateUserData( UserModel model ) {
        String query = "UPDATE Users SET fullName = ?, eMail = ?, password = ? WHERE id = ?";
        try {
            if ( connection != null ) {
                EstablishConnection( );
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setString( 1, model.getFullName( ) );
                preparedStatement.setString( 2, model.geteMail( ) );
                preparedStatement.setString( 3, model.getPassword( ) );
                preparedStatement.setLong( 4, model.getId( ) );
                return preparedStatement.executeUpdate( );
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getStackTrace( ) );
        }
        return - 1;
    }

    public int UpdateProductData( ProductModel model ) {
        String query = "UPDATE Users SET name = ?, categoryId = ?, imageURL = ?, price = ? WHERE id = ?";
        try {
            if ( connection != null ) {
                EstablishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setString( 1, model.getName( ) );
                preparedStatement.setLong( 2, model.getCategoryId( ) );
                preparedStatement.setString( 3, model.getImageUrl( ) );
                preparedStatement.setDouble( 4, model.getPrice( ) );
                preparedStatement.setLong( 5, model.getId( ) );
                return preparedStatement.executeUpdate( query );
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getStackTrace( ) );
        }
        return - 1;
    }

    public int UpdateCategory( CategoryModel model ) {
        String query = "UPDATE Category SET name = ? WHERE id = ?";
        try {
            if ( connection != null ) {
                EstablishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setString( 1, model.getName( ) );
                preparedStatement.setInt( 2, model.getId( ) );
                return preparedStatement.executeUpdate( query );
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return - 1;
    }

    public int DeleteFromTable( String tableName, long Id ) {
        String query = String.format( "UPDATE %c SET isActive = 0 WHERE id = ?",tableName);
        try {
            if ( connection != null ) {
                EstablishConnection( );
                PreparedStatement preparedStatement = connection.prepareStatement( query );
                preparedStatement.setLong( 1, Id );
                return preparedStatement.executeUpdate( );
            }
        } catch ( Exception exc ) {
            System.out.println( exc.getMessage( ) );
        }
        return - 1;
    }
}
