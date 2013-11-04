package alpro3.grafos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Grafo3Test {

	@Test
	public void testFontes() {
		Grafo3 g = AppGrafo0.loadFile("g2.txt");
		List<Integer> actual = g.getFontes();
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(1);
		expected.add(4);
		assertEquals(expected, actual);
	}

	@Test
	public void testSumidouros() {
		Grafo3 g = AppGrafo0.loadFile("g2.txt");
		List<Integer> actual = g.getSumidouros();
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(6);
		assertEquals(expected, actual);
	}
}
