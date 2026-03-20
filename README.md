# Rincon-post2-u3algoritmos
Evidencias
Checkpoint 1
Checkpoint 1.jpg
Checkpoint 2
Checkpoint 2.jpg
Checkpoint 3
Checkpoint 3.jpg
Análisis comparativo de nodos explorados
El análisis experimental muestra que el número de nodos explorados por la versión naive crece exponencialmente conforme aumenta n. Esta variante realiza verificación de conflictos en tiempo O(n) por cada intento y explora gran parte del árbol de búsqueda sin optimización estructural. En contraste, la versión con bitmask reduce significativamente el número de nodos explorados al representar restricciones de columnas y diagonales mediante operaciones bitwise en tiempo O(1). Esta mejora no cambia la complejidad asintótica exponencial del problema, pero reduce drásticamente las constantes ocultas.
La razón de reducción (nodesBitmask / nodesNaive) disminuye conforme n aumenta, lo que indica que la poda se vuelve relativamente más efectiva en instancias grandes. A medida que el espacio de búsqueda crece factorialmente, la capacidad de descartar rápidamente configuraciones inválidas mediante máscaras binarias evita la exploración innecesaria de grandes subárboles.
Existe además una relación directa entre la eficiencia de la poda y la densidad de soluciones válidas. Para valores pequeños de n, donde la proporción entre configuraciones válidas e inválidas es mayor, la ventaja es moderada. Sin embargo, conforme n crece, la densidad de soluciones válidas disminuye drásticamente frente al total de configuraciones posibles, lo que hace que la capacidad de podar rápidamente conflictos sea crucial. En consecuencia, el método bitmask se vuelve significativamente más eficiente conforme aumenta la dificultad combinatoria del problema.
