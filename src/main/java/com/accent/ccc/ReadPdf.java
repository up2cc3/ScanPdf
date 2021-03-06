package com.accent.ccc;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.ghost4j.document.Document;
import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.modifier.ModifierException;
import org.ghost4j.modifier.SafeAppenderModifier;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
                    resultado = resultado.substring(0, 5);
                    System.out.println(resultado);

                    File newfile = new File(carpetaFinal + resultado + ".pdf");
                    if (newfile.exists()) {
                        PDFDocument newpdf = new PDFDocument();
                        newpdf.load(new File(carpetaFinal + resultado + ".pdf"));
                        PDFDocument myPdf = new PDFDocument();
                        myPdf.load(f.getImageFile());
                        SafeAppenderModifier modifier = new SafeAppenderModifier();
                        Map<String, Serializable> parameters = new HashMap<String, Serializable>();
                        parameters.put(SafeAppenderModifier.PARAMETER_APPEND_DOCUMENT, myPdf);
                        Document apennd = modifier.modify(newpdf, parameters);
                        apennd.write(new File(carpetaFinal + resultado + ".pdf"));
                        f.getImageFile().delete();

                    } else {


                        if (f.getImageFile().renameTo(newfile)) {
                            System.out.println("Rename succesful");
                        } else {
                            System.out.println("Rename failed");
                        }
                    }
                }

            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (ModifierException e) {
                e.printStackTrace();
            }
    }

}

