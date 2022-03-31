package test;

import LifeGame.Cell;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** 
* LifeGame.Cell Tester.
* 
* @author <Authors name> 
* @since <pre>5ÔÂ 14, 2021</pre> 
* @version 1.0 
*/ 
public class CellTest {
    private static Cell cell = new Cell();
@Before
public void before() throws Exception {

}

@After
public void after() throws Exception {

} 

/** 
* 
* Method: setAlive(boolean alive) 
* 
*/ 
@Test
public void testSetAlive() throws Exception {
    cell.setAlive(true);
    assertEquals(true,cell.getAlive());
} 

/** 
* 
* Method: changeAlive() 
* 
*/ 
@Test
public void testChangeAlive() throws Exception {
    boolean status = cell.getAlive();
    cell.changeAlive();
    assertEquals(!status,cell.getAlive());
} 

/** 
* 
* Method: getAlive() 
* 
*/ 
@Test
public void testGetAlive() throws Exception { 

} 

} 
