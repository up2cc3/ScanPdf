package com.text.examples.tess4jId;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


public class App
{
    public String getImgText(String imagelocation){
        ITesseract instance=new Tesseract();
        try {
            //instance.setDatapath(LoadLobs.extractTessResources("tessdata").getParent());
            instance.setLanguage("eng");
            String imgText = instance.doOCR(new File(imagelocation));
            return imgText;
        } catch (TesseractException e){
            e.getMessage();
            return "Error while reading image";
        }

    }
    public static void main(String[]args){
        App app=new App();
        System.out.println(app.getImgText("images/x.jpg"));
    }
}

