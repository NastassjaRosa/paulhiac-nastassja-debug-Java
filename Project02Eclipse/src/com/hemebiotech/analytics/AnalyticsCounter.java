package com.hemebiotech.analytics;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;


/**
 * The type Analytics counter.
 */
public class AnalyticsCounter {
	private static int headacheCount = 0;	// initialize to 0
	private static int rashCount = 0;		// initialize to 0
	private static int pupilCount = 0;		// initialize to 0

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String [] args) throws Exception {
        System.out.println("Répertoire courant : " + System.getProperty("user.dir"));

        // first get input
        int headacheCount;
        try (BufferedReader reader = new BufferedReader(new FileReader("symptoms.txt"))) {
            String line = reader.readLine();

            headacheCount = 0;
            while (line != null) {
                System.out.println("symptom from file: " + line);
                if (line.equals("headache")) {
                    headacheCount++;
                    System.out.println("number of headaches: " + headacheCount);
                } else if (line.equals("rash")) {
                    rashCount++;
                } else if (line.contains("pupils")) {
                    pupilCount++;
                }

                line = reader.readLine();    // get another symptom
            }
        }

        // next generate output
        try (FileWriter writer = new FileWriter("result.out")) {
            writer.write("headache: " + headacheCount + "\n");
            writer.write("rash: " + rashCount + "\n");
            writer.write("dilated pupils: " + pupilCount + "\n");
        }
    }
}
