package com.u3.lab2;

import org.junit.jupiter.api.Test;

import com.u3.lab2.nqueens.NQueens;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NQueensTest {

    // Valores conocidos del problema N-Queens
    private static final long[] EXPECTED = {
            0,      // índice 0 (no usado)
            1,      // n = 1
            0,      // n = 2
            0,      // n = 3
            2,      // n = 4
            10,     // n = 5
            4,      // n = 6
            40,     // n = 7
            92,     // n = 8
            352,    // n = 9
            724,    // n = 10
            2680,   // n = 11
            14200   // n = 12
    };

    @Test
    void testValoresReferencia() {
        // Verificar valores específicos pedidos
        assertEquals(1, NQueens.solveNaive(1));
        assertEquals(2, NQueens.solveNaive(4));
        assertEquals(92, NQueens.solveNaive(8));
        assertEquals(14200, NQueens.solveNaive(12));

        assertEquals(1, NQueens.solveBitmask(1));
        assertEquals(2, NQueens.solveBitmask(4));
        assertEquals(92, NQueens.solveBitmask(8));
        assertEquals(14200, NQueens.solveBitmask(12));
    }

    @Test
    void testNaiveVsBitmaskIgualesHasta12() {
        for (int n = 1; n <= 12; n++) {
            long naive = NQueens.solveNaive(n);
            long bitmask = NQueens.solveBitmask(n);

            assertEquals(EXPECTED[n], naive,
                    "Valor incorrecto en naive para n=" + n);

            assertEquals(EXPECTED[n], bitmask,
                    "Valor incorrecto en bitmask para n=" + n);

            assertEquals(naive, bitmask,
                    "Las variantes difieren en n=" + n);
        }
    }
}