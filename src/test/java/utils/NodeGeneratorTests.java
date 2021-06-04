package utils;

import layers.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NodeGeneratorTests {

    @Test
    void nodeGenerator_success(){
        Node temp1 = NodeGenerator.generate();
        Node temp2 = NodeGenerator.generate();

        Assertions.assertNotNull(temp1);
        Assertions.assertNotNull(temp2);

        Assertions.assertNotEquals(temp1.getWeight(), temp2.getWeight());
    }

}
