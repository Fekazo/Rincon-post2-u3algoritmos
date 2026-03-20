package com.u3.lab2.nqueens;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Análisis comparativo de nodos explorados:
 * variante naive vs variante bitmask.
 */
public class NQueensAnalysis {

    /**
     * Clase que encapsula el resultado del análisis.
     */
    public static class Result {

        private final int n;
        private final long solutions;
        private final long nodesNaive;
        private final long nodesBitmask;

        public Result(int n,
                      long solutions,
                      long nodesNaive,
                      long nodesBitmask) {
            this.n = n;
            this.solutions = solutions;
            this.nodesNaive = nodesNaive;
            this.nodesBitmask = nodesBitmask;
        }

        public int getN() {
            return n;
        }

        public long getSolutions() {
            return solutions;
        }

        public long getNodesNaive() {
            return nodesNaive;
        }

        public long getNodesBitmask() {
            return nodesBitmask;
        }

        /**
         * Razón de reducción:
         * nodesBitmask / nodesNaive
         */
        public double reductionRatio() {
            return nodesNaive == 0
                    ? 1.0
                    : (double) nodesBitmask / nodesNaive;
        }

        @Override
        public String toString() {
            return "n=" + n +
                    ", solutions=" + solutions +
                    ", nodesNaive=" + nodesNaive +
                    ", nodesBitmask=" + nodesBitmask +
                    ", reductionRatio=" + reductionRatio();
        }
    }

    /**
     * Ejecuta el análisis para un valor n.
     */
    public static Result analyze(int n) {

        AtomicLong nodesNaive = new AtomicLong(0);
        AtomicLong nodesBitmask = new AtomicLong(0);

        long solutions = countNaive(n, 0, new int[n], nodesNaive);

        countBitmask(n, 0, 0, 0, 0, nodesBitmask);

        return new Result(
                n,
                solutions,
                nodesNaive.get(),
                nodesBitmask.get()
        );
    }

    // ============================================================
    // Variante naive con conteo de nodos
    // ============================================================

    private static long countNaive(int n,
                                   int row,
                                   int[] cols,
                                   AtomicLong nodes) {

        nodes.incrementAndGet();

        if (row == n) return 1;

        long count = 0;

        for (int col = 0; col < n; col++) {
            if (isValid(cols, row, col)) {
                cols[row] = col;
                count += countNaive(n, row + 1, cols, nodes);
            }
        }

        return count;
    }

    private static boolean isValid(int[] cols,
                                   int row,
                                   int col) {

        for (int r = 0; r < row; r++) {
            if (cols[r] == col ||
                Math.abs(cols[r] - col) == row - r)
                return false;
        }

        return true;
    }

    // ============================================================
    // Variante bitmask con conteo de nodos
    // ============================================================

    private static long countBitmask(int n,
                                     int row,
                                     int cols,
                                     int diag1,
                                     int diag2,
                                     AtomicLong nodes) {

        nodes.incrementAndGet();

        if (row == n) return 1;

        long count = 0;

        int available = ((1 << n) - 1) & ~(cols | diag1 | diag2);

        while (available != 0) {
            int bit = available & (-available);
            available -= bit;

            count += countBitmask(
                    n,
                    row + 1,
                    cols | bit,
                    (diag1 | bit) << 1,
                    (diag2 | bit) >> 1,
                    nodes
            );
        }

        return count;
    }
}