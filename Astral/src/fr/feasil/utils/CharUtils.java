package fr.feasil.utils;

import java.text.Normalizer;

public class CharUtils {
	
	public static String removeAccent(String source) {
		return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
	}
}
