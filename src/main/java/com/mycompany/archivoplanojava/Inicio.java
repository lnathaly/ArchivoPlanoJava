/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.archivoplanojava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Martinez
 */
public class Inicio {

    //List<Persona> listaPersona;
    List<Reporte> listaReporte;
    List<Persona> listaPersona = new ArrayList<>();

    public void menu() {
        int opcion = 0;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Bienvenido");
        System.out.println("1. Ingresar");
        System.out.println("2. Eliminar");
        System.out.println("3. Mostrar");
        System.out.print("Digite la Opcion: ");
        opcion = entrada.nextInt();
        Inicio ini = new Inicio();

        switch (opcion) {
            case 1:
                ini.ingreserPersonaReporte();
                break;
            case 2: {
                try {
                    listaPersona = ini.leerArchivo("Documentos/archivo.txt");
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ini.eliminaReporte(listaPersona);
            break;
            case 3: {
                try {
                    listaPersona = ini.leerArchivo("Documentos/archivo.txt");
                } catch (Exception ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            ini.imprimepersona(listaPersona);

            break;

        }

    }

    public void ingreserPersonaReporte() {

        Scanner entrada = new Scanner(System.in);
        int opc = 1;
        int opc2 = 1;
        String cadena = "";

        while (opc == 1) {

            System.out.print("Ingrese la cedula de la persona: ");
            int cedula = entrada.nextInt();
            entrada.nextLine();

            System.out.print("Ingrese el nombre de la persona: ");
            String nombre = entrada.nextLine();

            System.out.print("Ingrese el apellido de la persona: ");
            String apellido = entrada.nextLine();

            System.out.print("Ingrese el correo de la persona: ");
            String correo = entrada.nextLine();

            cadena = cadena + cedula + "," + nombre + "," + apellido + "," + correo + ",";
            opc2 = 1;

            while (opc2 == 1) {
                System.out.print("Ingrese el codigo del reporte: ");
                int codigo = entrada.nextInt();
                entrada.nextLine();

                System.out.print("Ingrese el nombre de la empresa: ");
                String empresa = entrada.nextLine();

                System.out.print("Ingrese la descripcion del reporte: ");
                String descrip = entrada.nextLine();

                System.out.print("Ingrese el estado del reporte: ");
                boolean estado = entrada.nextBoolean();

                System.out.print("Ingrese el valor del reporte: ");
                int valor = entrada.nextInt();
                entrada.nextLine();
                cadena = cadena + codigo + "-" + empresa + "-" + descrip + "-" + estado + "-" + valor + "%";
                System.out.print("Digite 1 para ingresar otro reporte: ");
                opc2 = entrada.nextInt();

            }

            System.out.print("Digite 1 para ingresar otra persona: ");
            opc = entrada.nextInt();
            cadena = cadena + "!";

        }

        System.out.print(cadena);
        FileWriter fichero = null;

        try {

            fichero = new FileWriter("Documentos/archivo.txt", true);
            BufferedWriter bfwriter = new BufferedWriter(fichero);
            String[] cad = cadena.split("!");
            int longitud = cad.length;
            for (int i = 0; i < longitud; i++) {
                bfwriter.write(cad[i]);
                bfwriter.newLine();

            }

            bfwriter.close();
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Success...");
        //for (Persona lisPersona : listaPersona) {
        //System.out.println("Cedula:" + lisPersona.getCedula() + "Nombre: " + lisPersona.getNombre() + "Apellido: " + lisPersona.getApellido() + "Correo: " + lisPersona.getCorreo());
        //}

    }

    public List<Persona> leerArchivo(String archivo) throws Exception {
        List<Persona> listaPersona = new ArrayList<>();
        //List<Reporte> listaReporte= new ArrayList<>();
        String cadena;
        FileReader f;
        BufferedReader b;
        try {
            f = new FileReader(archivo);
            b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                List<Reporte> listaReporte = new ArrayList<>();
                String[] personaVector = cadena.split(",");
                Persona persona = new Persona(Integer.parseInt(personaVector[0]), personaVector[1], personaVector[2], personaVector[3]);
                listaPersona.add(persona);
                String[] reportes = personaVector[4].split("%");
                int longitud = reportes.length;

                for (int i = 0; i < longitud; i++) {

                    String[] repor = reportes[i].split("-");
                    Reporte reporte = new Reporte(Integer.parseInt(repor[0]), repor[1], repor[2], Boolean.valueOf(repor[3]), Integer.parseInt(repor[4]));
                    listaReporte.add(reporte);
                    persona.setReporte(listaReporte);

                }

                persona.setReporte(listaReporte);
            }
            b.close();
            return listaPersona;

        } catch (FileNotFoundException ex) {
            System.err.print("No se puede encontra el archivo");
            throw new FileNotFoundException("No se puede encontra el archivo");
        } catch (IOException ex) {
            System.err.print("Error al leer el archivo");
            throw new IOException("\033[31mError al leer el archivo");
        }

    }

    public void imprimepersona(List<Persona> list) {

        try {
            listaPersona = list;
        } catch (Exception ex) {
            System.err.print("Error al leer el archivo");
        }
        for (Persona x : listaPersona) {
            System.out.println(" Nombre: " + x.getNombre() + " Apellido: " + x.getApellido() + " correo: " + x.getCorreo());
            for (Reporte r : x.getReporte()) {
                if (r.isEstado() == false) {
                    System.out.println(" codigo: " + r.getCodigo() + " Empresa: " + r.getEmpresa() + " Descricion: " + r.getDescripcion() + " Valor: " + r.getValor());
                }
            }

        }
    }

    public void eliminaReporte(List<Persona> list) {

        try {
            listaPersona = list;
        } catch (Exception ex) {
            System.err.print("Error al leer el archivo");
        }
        for (Persona x : listaPersona) {
            System.out.println("Cedula: " + x.getCedula() + " Nombre: " + x.getNombre() + " Apellido: " + x.getApellido() + " correo: " + x.getCorreo());
            for (Reporte r : x.getReporte()) {
                if (r.isEstado() == false) {
                    System.out.println(" codigo: " + r.getCodigo() + " Empresa: " + r.getEmpresa() + " Descricion: " + r.getDescripcion() + " Valor: " + r.getValor());
                }
            }

        }
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese la cedula de la persona: ");
        int cedula = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Ingrese el codigo del reporte: ");
        int codigo = entrada.nextInt();
        entrada.nextLine();
        for (Persona x : listaPersona) {
            if (x.getCedula() == cedula) {
                for (Reporte r : x.getReporte()) {
                    if (r.getCodigo() == codigo) {
                        r.setEstado(true);
                    }
                }

            }

        }
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("Documentos/archivo.txt");
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            for (Persona persona : listaPersona) {
                //escribe los datos en el archivo
                bfwriter.write(persona.getCedula() + "," + persona.getNombre() + "," + persona.getApellido() + "," + persona.getCorreo() + ",");
                for (Reporte r : persona.getReporte()) {
                    bfwriter.write(r.getCodigo() + "-" + r.getEmpresa() + "-" + r.getDescripcion() + "-" + r.isEstado() + "-" + r.getValor() + "%");

                }
                bfwriter.newLine();
            }
            //cierra el buffer intermedio
            bfwriter.close();
            System.out.println("Archivo creado satisfactoriamente..");

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
