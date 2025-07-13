package Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentificableTest {
    static class TestIdentificable extends IdentificableAbstracta{ TestIdentificable(String id) {super(id);}}
    private TestIdentificable a, b, c;

    @BeforeEach
    void setUp() {
        a = new TestIdentificable("111");
        b = new TestIdentificable("111");
        c = new TestIdentificable("222");
    }

    @Test
    void testEquals(){
        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void testHashCode(){
        assertEquals(a.hashCode(), b.hashCode());
    }
}