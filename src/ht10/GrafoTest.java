package ht10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrafoTest {

	@Test
	void test() {
		Grafo graph = new Grafo();
		graph.add("Fraijanes", "Muxbal", "15");
		assertEquals(15,graph.getDistance("Fraijanes", "Muxbal"));
	}

}
