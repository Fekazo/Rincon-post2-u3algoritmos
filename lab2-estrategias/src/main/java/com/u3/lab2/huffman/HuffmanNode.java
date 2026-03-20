package com.u3.lab2.huffman;

import java.util.Objects;

// ...existing code...
/**
 * Nodo del árbol de Huffman.
 * 
 * Las hojas representan símbolos individuales.
 * Los nodos internos agrupan frecuencias de sus hijos.
 */
public final class HuffmanNode implements Comparable<HuffmanNode> {
    private final char symbol;
    private final int freq;
    private final HuffmanNode left;
    private final HuffmanNode right;

    public HuffmanNode(char symbol, int freq, HuffmanNode left, HuffmanNode right) {
        this.symbol = symbol;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public char symbol() {
        return symbol;
    }

    public int freq() {
        return freq;
    }

    public HuffmanNode left() {
        return left;
    }

    public HuffmanNode right() {
        return right;
    }

    /**
     * Los nodos se ordenan por frecuencia ascendente
     * para su uso en un min-heap (PriorityQueue).
     */
    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.freq, other.freq);
    }

    /**
     * Retorna true si el nodo es una hoja
     * (no tiene hijos izquierdo ni derecho).
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HuffmanNode)) return false;
        HuffmanNode other = (HuffmanNode) obj;
        return symbol == other.symbol
                && freq == other.freq
                && Objects.equals(left, other.left)
                && Objects.equals(right, other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, freq, left, right);
    }

    @Override
    public String toString() {
        return "HuffmanNode[symbol=" + symbol + ", freq=" + freq + ", left=" + left + ", right=" + right + "]";
    }
}