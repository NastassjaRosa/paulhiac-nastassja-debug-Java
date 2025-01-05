package com.hemebiotech.analytics;

import java.util.Map;

public interface ISymptomWriter {

    //ici déclare la map ou vont s'écrire les symtomes
    void writeSymptoms(Map<String, Integer> symptoms);
}
