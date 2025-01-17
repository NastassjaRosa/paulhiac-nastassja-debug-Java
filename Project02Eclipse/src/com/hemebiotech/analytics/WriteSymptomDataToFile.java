package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter {
    //variable filePath stocke le chemin du fichier de sortie
    private String filePath;


    //Constructeur pour le path
    public WriteSymptomDataToFile(String filePath) {
        this.filePath = filePath;
    }

    //Methode writeSymptoms
    @Override
    public void writeSymptoms(Map<String, Integer> symptoms){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }

}
