package com.u3.lab2.huffman;

import com.u3.lab2.nqueens.NQueensAnalysis;

public class Main {

    public static void main(String[] args) {

        System.out.printf("%-4s %-12s %-18s %-18s %-15s%n",
                "n", "Solutions", "Nodes Naive", "Nodes Bitmask", "Reduction");

        System.out.println("-------------------------------------------------------------------------------");

        for (int n = 6; n <= 14; n++) {

            NQueensAnalysis.Result r = NQueensAnalysis.analyze(n);

            System.out.printf("%-4d %-12d %-18d %-18d %-15.6f%n",
                    r.getN(),
                    r.getSolutions(),
                    r.getNodesNaive(),
                    r.getNodesBitmask(),
                    r.reductionRatio());
        }
    }
}