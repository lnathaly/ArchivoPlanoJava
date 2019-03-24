/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.archivoplanojava;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Alexander Martinez
 */
public class Principal {

    public static void main(String[] args) throws Exception {

        Inicio ini = new Inicio();
        ini.menu();
    }
}
