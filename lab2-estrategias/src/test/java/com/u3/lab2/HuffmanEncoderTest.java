package com.u3.lab2;

import org.junit.jupiter.api.Test;

import com.u3.lab2.huffman.HuffmanEncoder;
import com.u3.lab2.huffman.HuffmanNode;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanEncoderTest {

    private static final String TEXT = "aabbbcccc";

    private Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqs = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqs.put(c, freqs.getOrDefault(c, 0) + 1);
        }
        return freqs;
    }

    @Test
    void testLongitudesEsperadas() {
        Map<Character, Integer> freqs = buildFrequencyMap(TEXT);
        HuffmanNode root = HuffmanEncoder.buildTree(freqs);
        Map<Character, String> codes = HuffmanEncoder.generateCodes(root);

        int lenA = codes.get('a').length(); // freq 2
        int lenB = codes.get('b').length(); // freq 3
        int lenC = codes.get('c').length(); // freq 4

        // c es el más frecuente → debe tener código más corto o igual
        assertTrue(lenC <= lenB, "c no debe tener código más largo que b");
        assertTrue(lenC <= lenA, "c no debe tener código más largo que a");

        // a es el menos frecuente → no debe tener código más corto que c
        assertTrue(lenA >= lenC, "a no debe tener código más corto que c");
    }

    @Test
    void testPropiedadDePrefijo() {
        Map<Character, Integer> freqs = buildFrequencyMap(TEXT);
        HuffmanNode root = HuffmanEncoder.buildTree(freqs);
        Map<Character, String> codes = HuffmanEncoder.generateCodes(root);

        for (String code1 : codes.values()) {
            for (String code2 : codes.values()) {
                if (!code1.equals(code2)) {
                    assertFalse(code2.startsWith(code1),
                            "Violación de propiedad de prefijo: " + code1 + " es prefijo de " + code2);
                }
            }
        }
    }

    @Test
    void testCompresionMejorQueASCII() {
        Map<Character, Integer> freqs = buildFrequencyMap(TEXT);
        HuffmanNode root = HuffmanEncoder.buildTree(freqs);
        Map<Character, String> codes = HuffmanEncoder.generateCodes(root);

        int totalBitsHuffman = 0;

        for (char c : TEXT.toCharArray()) {
            totalBitsHuffman += codes.get(c).length();
        }

        int totalBitsASCII = TEXT.length() * 8;

        assertTrue(totalBitsHuffman < totalBitsASCII,
                "La codificación Huffman debe usar menos bits que ASCII");
    }
}
