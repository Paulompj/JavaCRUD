package DAO;

import Entidades.Usuario;

import java.util.Scanner;
public class Functions {
    static Scanner reader = null;
    public static Usuario registerUser(){
        Usuario user = null;
        try{
            reader = new Scanner(System.in);
            System.out.println("Enter the user name: ");
            String name = reader.nextLine();
            System.out.println("Enter the age: ");
            String age = String.valueOf(reader.nextInt());
            user = new Usuario(age,name);
            return user;
        }catch (RuntimeException e){
            System.out.println("Error the registerUser user: "+e.getMessage());
        }
        return null;
    }
    public static int registerId(){
        reader = new Scanner(System.in);
        int id = 0;
        try{
            System.out.println("Enter the id this user: ");
            id = reader.nextInt();
        }catch (RuntimeException e){
            System.out.println("Error registering id"+e.getMessage());
        }
        return id;
    }
}
