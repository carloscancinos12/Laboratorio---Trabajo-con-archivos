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
    
    private void escrituraBinariaTamaño(int tamaño)
    {
        try
        {
            FileOutputStream archivo = new FileOutputStream("datos.bin");
            
            DataOutputStream data = new DataOutputStream(archivo);
            
            Scanner input = new Scanner (System.in);
            
            for(int i=0; i < tamaño; i++)
            {
                System.out.println("Ingrese el nombre de la persona No. " + i + ":");
                String nombre = input.next();
                System.out.println("Ingrese la edad de la persona No. " + i + ":");
                byte edad = input.nextByte();
                System.out.println("Ingrese el telefono de la persona No. " + i + ":");
                int tel = input.nextInt();
                int ancho = nombre.length();
                if(ancho < 36)
                {
                    for(int j = ancho; j<36; j++)
                    {
                        nombre = nombre + " ";
                    }
                }
                data.writeBytes(nombre);
                data.writeByte(edad);
                data.writeInt(tel);
            }
            archivo.close();
        } catch(FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void escrituraBinariaSeparada(int tamaño)
    {
        try
        {
            FileOutputStream archivo = new FileOutputStream("datos.bin");
            
            DataOutputStream data = new DataOutputStream(archivo);
            
            Scanner input = new Scanner (System.in);
            
            for(int i=0; i < tamaño; i++)
            {
                System.out.println("Ingrese el nombre de la persona No." + i + ":");
                String n = input.next();
                System.out.println("Ingrese la edad de la persona No." + i + ":");
                byte e = input.nextByte();
                System.out.println("Ingrese el telefono de la persona No." + i + ":");
                int t = input.nextInt();
                
                data.writeBytes(n);
                data.writeBytes("*");
                data.writeByte(e);
                data.writeBytes("*");
                data.writeInt(t);
                data.writeBytes("&");
            }
            
            archivo.close();
        } catch(FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new  Main();
        
        Scanner input = new Scanner (System.in);
        boolean salir = false;
        
        System.out.println("Ingrese la cantidad de personas que desea guardar:");
        int tamanio = input.nextInt();
        
        main.escrituraBinariaTamaño(tamanio);
        main.escrituraBinariaSeparada(tamanio);
    }
    
}