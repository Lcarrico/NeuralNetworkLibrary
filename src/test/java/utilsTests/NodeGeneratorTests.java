package utilsTests;

import layers.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.NodeGenerator;

/**
 * This test class is for the NodeGenerator class.
 * @see NodeGenerator
 */
public class NodeGeneratorTests {

    /**
     * This test is for NodeGenerator.generate()
     * @see NodeGenerator#generate()
     */
    @Test
    void nodeGenerator_success(){
        Node temp1 = NodeGenerator.generate();
        Node temp2 = NodeGenerator.generate();

        Assertions.assertNotNull(temp1);
        Assertions.assertNotNull(temp2);

        Assertions.assertNotEquals(temp1.getWeight(), temp2.getWeight());
    }

}
