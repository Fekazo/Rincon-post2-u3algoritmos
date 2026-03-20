package com.u3.lab2.nqueens;

/**
 * Problema N-Queens.
 * 
 * Incluye dos variantes:
 * 1) Backtracking naive (verificación O(n) por posición).
 * 2) Backtracking con bitmask (verificación O(1) por posición).
 */
public class NQueens {

    // ============================================================
    // VARIANTE 1: Naive
    // ============================================================

    /**
     * Resuelve N-Queens usando backtracking clásico.
     *
     * Complejidad temporal: Exponencial (~O(n!)).
     * Verificación de conflictos: O(n) por intento.
     *
     * @param n tamaño del tablero
     * @return número de soluciones válidas
     */
    public static long solveNaive(int n) {
        return naiveHelper(n, 0, new int[n]);
    }

    private static long naiveHelper(int n, int row, int[] cols) {
        if (row == n) return 1;

        long count = 0;

        for (int col = 0; col < n; col++) {
            if (isValid(cols, row, col)) {
                cols[row] = col;
                count += naiveHelper(n, row + 1, cols);
            }
        }

        return count;
    }

    /**
     * Verifica conflictos con reinas anteriores.
     * Costo: O(n)
     */
    private static boolean isValid(int[] cols, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (cols[r] == col) return false;  // misma columna
            if (Math.abs(cols[r] - col) == row - r) return false; // misma diagonal
        }
        return true;
    }

    // ============================================================
    // VARIANTE 2: Bitmask
    // ============================================================

    /**
     * Resuelve N-Queens usando representación por bits.
     *
     * Complejidad temporal: Exponencial (~O(n!)).
     * Verificación de conflictos: O(1) usando operaciones bitwise.
     *
     * @param n tamaño del tablero
     * @return número de soluciones válidas
     */
    public static long solveBitmask(int n) {
        return bitmaskHelper(n, 0, 0, 0, 0);
    }

    /**
     * @param cols  columnas ocupadas
     * @param diag1 diagonales principales ocupadas
     * @param diag2 diagonales secundarias ocupadas
     */
    private static long bitmaskHelper(int n,
                                      int row,
                                      int cols,
                                      int diag1,
                                      int diag2) {

        if (row == n) return 1;

        long count = 0;

        int available = ((1 << n) - 1) & ~(cols | diag1 | diag2);

        while (available != 0) {
            int bit = available & (-available); // bit menos significativo
            available -= bit;

            count += bitmaskHelper(
                    n,
                    row + 1,
                    cols | bit,
                    (diag1 | bit) << 1,
                    (diag2 | bit) >> 1
            );
        }

        return count;
    }
}