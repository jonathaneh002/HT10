package ht10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrafoTestNodos {

	@Test
	void test() {
		Grafo graph = new Grafo();
		graph.add("Fraijanes", "Muxbal", "15");
		
		assertEquals(true,graph.cityIn("Fraijanes", "Muxbal"));
		
	}
	}

