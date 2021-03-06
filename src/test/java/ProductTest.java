import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class ProductTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void product_instantiatesCorrectly_true() {
    Product testProduct = new Product("Bubbles", 1, 2);
    assertEquals(true, testProduct instanceof Product);
  }

  @Test
  public void Product_instantiatesWithName_String() {
    Product testProduct = new Product("Bubbles", 1, 2);
    assertEquals("Bubbles", testProduct.getName());
  }

  // @Test
  // public void Product_instantiatesWithCustomerId_int() {
  //   Product testProduct = new Product("Bubbles", 1);
  //   assertEquals(1, testProduct.getCustomerId());
  // }

  @Test
  public void equals_returnsTrueIfNameAndCustomerIdAreSame_true() {
    Product testProduct = new Product("Bubbles", 1, 2);
    Product anotherProduct = new Product("Bubbles", 1, 2);
    assertTrue(testProduct.equals(anotherProduct));
  }

  @Test
  public void save_successfullyAddsProductToDatabase_List() {
    Product testProduct = new Product("Bubbles", 1, 2);
    testProduct.save();
    assertTrue(Product.all().get(0).equals(testProduct));
  }

  @Test
  public void save_assignsIdToProduct() {
    Product testProduct = new Product("Bubbles", 1, 2);
    testProduct.save();
    Product savedProduct = Product.all().get(0);
    assertEquals(savedProduct.getId(), testProduct.getId());
  }

  @Test
  public void all_returnsAllInstancesOfProduct_true() {
    Product firstProduct = new Product("Bubbles", 1, 2);
    firstProduct.save();
    Product secondProduct = new Product("Spud", 3, 4);
    secondProduct.save();
    assertEquals(true, Product.all().get(0).equals(firstProduct));
    assertEquals(true, Product.all().get(1).equals(secondProduct));
  }

  @Test
  public void find_returnsProductWithSameId_secondProduct() {
    Product firstProduct = new Product("Bubbles", 1, 2);
    firstProduct.save();
    Product secondProduct = new Product("Spud", 3, 4);
    secondProduct.save();
    assertEquals(Product.find(secondProduct.getId()), secondProduct);
  }
}
