package ht10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrafoTestFloyd {

	@Test
	void test() {
		Grafo graph = new Grafo();
		graph.add("Fraijanes", "Guatemala", "100");
		graph.add("Fraijanes", "Muxbal", "20");
		graph.add("Muxbal", "Guatemala", "20");
		
		graph.floydAlgorithm();
		
		assertEquals(40,graph.getDistance("Fraijanes", "Guatemala"));
	}

}
