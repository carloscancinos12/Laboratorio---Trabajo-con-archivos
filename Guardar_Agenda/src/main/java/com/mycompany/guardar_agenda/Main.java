/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guardar_agenda;

/**
 *
 * @author Triasic Ranger
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private void leerArchivo(){
        try{
            FileReader lectorArchivo = new FileReader("hola.txt");
            
            int caracter;
                   
            do{
                caracter = lectorArchivo.read();
                if(caracter == -1){
                    
                }
                else{
                   System.out.println("Codigo ASCII: " + caracter + ", caracter: " + (char)caracter);
                }
            }while(caracter>-1);
            
            lectorArchivo.close();
        } catch(FileNotFoundException ex){
            System.out.println("No encontré el archivo solicitado");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);//Leva un registro e informa al sistema operativo de los errores, guarda los errores.
        } catch(IOException ex){
            System.out.println("Tengo problemas para cerrar el archivo");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void escribirArchivo(){
        try{
            FileWriter escritorArchivo = new FileWriter("hola2.txt");
            
            escritorArchivo.write(70);
            escritorArchivo.write("HOla mundo");
            
            escritorArchivo.close();
        } catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void lecturaAleatoria(){
        try {
            RandomAccessFile archivo = new RandomAccessFile("datos.bin", "r");
            long longitudEnBytes = archivo.length();
            archivo.seek(19);
            
            //byte letras[] = new byte[4];
            byte numeros[] = new byte[4];
            
            int finDeArchivo = archivo.readInt();//Este guarda el numero que acabamos de guardar
            //if(finDeArchivo == -1){
            //    System.out.println("Encontre el fin del archivo");
            //}
            //System.out.println(new String(letras));
            System.out.println(finDeArchivo);
            
            archivo.seek(0);
            byte letras[] = new byte[4];
            archivo.read(letras);
            System.out.println(new String(letras));
            
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void escrituraAleatoria(){
        try{
            RandomAccessFile archivo = new RandomAccessFile("datos.bin", "rw");
            archivo.seek(archivo.length());
            archivo.writeFloat(30.5f);
            archivo.writeInt(2345667);
            archivo.close();
        } catch(FileNotFoundException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void escrituraBinariaTamaño(int tamanio){
        try{
            FileOutputStream archivo = new FileOutputStream("datos.bin");
            
            DataOutputStream data = new DataOutputStream(archivo);
            
            Scanner input = new Scanner (System.in);
            
            for(int i=0; i < tamanio; i++){
                System.out.println("Ingrese el nombre de la persona " + i + ":");
                String n = input.next();
                System.out.println("Ingrese la edad de la persona " + i + ":");
                byte e = input.nextByte();
                System.out.println("Ingrese el numero de telefono de la persona " + i + ":");
                int t = input.nextInt();
                int largo = n.length();
                if(largo < 30){
                    for(int j = largo; j<30; j++){
                        n = n + " ";
                    }
                }
                
                data.writeBytes(n);
                data.writeByte(e);
                data.writeInt(t);
            }
            
            archivo.close();
        } catch(FileNotFoundException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void escrituraBinariaSeparada(int tamanio){
        try{
            FileOutputStream archivo = new FileOutputStream("datos.bin");
            
            DataOutputStream data = new DataOutputStream(archivo);
            
            Scanner input = new Scanner (System.in);
            
            for(int i=0; i < tamanio; i++){
                System.out.println("Ingrese el nombre de la persona " + i + ":");
                String n = input.next();
                System.out.println("Ingrese la edad de la persona " + i + ":");
                byte e = input.nextByte();
                System.out.println("Ingrese el numero de telefono de la persona " + i + ":");
                int t = input.nextInt();
                
                data.writeBytes(n);
                data.writeBytes("*");
                data.writeByte(e);
                data.writeBytes("*");
                data.writeInt(t);
                data.writeBytes("#");
            }
            
            archivo.close();
        } catch(FileNotFoundException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void lecturaBinaria1(int tamanio){
        try{
          FileInputStream archivo = new FileInputStream("datos.bin");
          
          DataInputStream data = new DataInputStream(archivo);
          
          for(int i=0; i<tamanio; i++){
              System.out.println("--- Persona " + i + ": ---");
              
              byte nombreBytes[] = data.readNBytes(30);
              String nombre = new String(nombreBytes);
              System.out.println(nombre);
          
              int edad = data.read();
              System.out.println(edad);
          
              int telefono = data.readInt();
              System.out.println(telefono);
          }
          
          
                  
          archivo.close();
        }catch(FileNotFoundException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void lecturaBinaria2(int tamanio){
        try{
          FileInputStream archivo = new FileInputStream("datos.bin");
          
          DataInputStream data = new DataInputStream(archivo);
          
          for(int i=0; i<tamanio; i++){
              System.out.println("--- Persona " + i + ": ---");
              
              byte nombreBytes[] = data.readNBytes(30);
              String nombre = new String(nombreBytes);
              System.out.println(nombre);
              data.read();
          
              int edad = data.read();
              System.out.println(edad);
              data.read();
          
              int telefono = data.readInt();
              System.out.println(telefono);
              data.read();
          }
          
          
                  
          archivo.close();
        }catch(FileNotFoundException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new  Main();
        //main.leerArchivo();
        //main.escribirArchivo();
        //main.escrituraBinaria();
        //main.lecturaBinaria();
        //main.lecturaAleatoria();
        //main.escrituraAleatoria();
        
        Scanner input = new Scanner (System.in);
        boolean salir = false;
        
        System.out.println("Ingrese la cantidad de personas que desea guardar:");
        int tamanio = input.nextInt();
        
        //main.escrituraBinariaTamaño(tamanio);
        main.escrituraBinariaSeparada(tamanio);
        //main.lecturaBinaria1(tamanio);
        //main.lecturaBinaria2(tamanio);
    }
    
}