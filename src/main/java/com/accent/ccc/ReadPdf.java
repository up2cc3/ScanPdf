package com.accent.ccc;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.ghost4j.document.PDFDocument;

import java.awt.*;
import java.io.File;

/**
 * Created by ccc on 27/05/2017.
 */
public class ReadPdf {


    private static Rectangle area = new Rectangle(
            2060, 20, 300, 300);

    public static void main(String[] args) {
        String carpetaFinal = "C:\\Users\\ccc\\Documents\\Tesseract\\Sorted\\";
        String result = null;
        File imageFile = null;
        PDFDocument pdf = null;
        Ficheros ficheros = new Ficheros(result, imageFile, pdf);

        java.util.List<Ficheros> listaFicheros = ficheros.buscarFicheros();
        Tesseract instance = new Tesseract(); // JNA Interface Mapping
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
        for (Ficheros f : listaFicheros)
            try {
                String resultado = instance.doOCR(f.getImageFile(), area);
                if (resultado != null) {
                    resultado = resultado.substring(0,5);
                    System.out.println(resultado);

                    File newfile = new File(carpetaFinal + resultado + ".pdf");

                    if (f.getImageFile().renameTo(newfile)) {
                        System.out.println("Rename succesful");
                    } else {
                        System.out.println("Rename failed");
                    }
                }

            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
    }

}

