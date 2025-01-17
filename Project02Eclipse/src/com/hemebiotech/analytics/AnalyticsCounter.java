package com.hemebiotech.analytics;

import java.io.*;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * The type Analytics counter.
 */
public class AnalyticsCounter {
//	private static int headacheCount = 0;	// initialize to 0
//	private static int rashCount = 0;		// initialize to 0
//	private static int pupilCount = 0;		// initialize to 0

    // lier  AnalyticsCounter aux interfaces
    private ISymptomReader reader;
    private ISymptomWriter writer;

    // Constructeur = methode pour initialiser un objet (le reader et le writer)
    public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    // Methode qui recupere la list des symptomes
    public List<String> getSymptoms() {
        return reader.GetSymptoms();
    }

    // Methode qui compte lers occurances des symptomes de list en entrée
    public Map<String, Integer> countSymptoms(List<String> symptoms) {
        Map<String, Integer> symptomCount = new TreeMap<>();
        for (String symptom : symptoms) {
            symptomCount.put(symptom, symptomCount.getOrDefault(symptom, 0) + 1);
        }
        return symptomCount;
    }

    // Methode qui classe par ordre alphabetique
    public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
        return new TreeMap<>(symptoms); // Utilisation d'une TreeMap pour trier les clés
    }


    // Methode qui ecrit le fichier de sortie
    public void writeSymptoms(Map<String, Integer> symptoms) {
        writer.writeSymptoms(symptoms); // Appelle la méthode de ISymptomWriter
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Répertoire courant : " + System.getProperty("user.dir"));

        // Chemin fichier sortie
        String outputFilePath = "result.out";

        // Étape 1 : Initialiser le reader et le writer
        ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");
        ISymptomWriter writer = new WriteSymptomDataToFile("result.out");

        // Étape 2 : Instancier AnalyticsCounter
        AnalyticsCounter analytics = new AnalyticsCounter(reader, writer);

        // Étape 3 : Orchestrer les opérations
        List<String> symptoms = analytics.getSymptoms(); // Lire les symptômes
        Map<String, Integer> countedSymptoms = analytics.countSymptoms(symptoms); // Compter les occurrences
        Map<String, Integer> sortedSymptoms = analytics.sortSymptoms(countedSymptoms); // Trier les symptômes
        analytics.writeSymptoms(sortedSymptoms); // Écrire les résultats

        System.out.println("Les données ont été écrites dans : " + new File(outputFilePath).getAbsolutePath() + " !!!!!!!!!!!!!!!!!!!!!!");

    }
}
