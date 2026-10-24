import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import model.Courier;
import model.Customer;
import model.Delivery;
import model.DeliveryStatus;
import model.Package;
import repository.DeliveryRepository;
import repository.InMemoryDeliveryRepository;
import service.DeliveryService;

public class DeliveryServiceTest {

    @Test
    public void testCreateDelivery() {

        DeliveryRepository repository =
                new InMemoryDeliveryRepository();

        DeliveryService service =
                new DeliveryService(repository);

        Customer customer =
                new Customer(1, "Анна", "+375291234567");

        Package packageItem =
                new Package(1, "Документы", 2.5);

        Delivery delivery =
                service.createDelivery(1, packageItem, customer);

        assertNotNull(delivery);


        assertEquals(
                DeliveryStatus.CREATED,
                delivery.getStatus()
        );

        // assertEquals(
        //         DeliveryStatus.DELIVERED,
        //         delivery.getStatus()
        // );
    }


    @Test
    public void testChangeDeliveryStatus() {

        DeliveryRepository repository =
                new InMemoryDeliveryRepository();

        DeliveryService service =
                new DeliveryService(repository);

        Customer customer =
                new Customer(1, "Анна", "+375291234567");

        Courier courier =
                new Courier(1, "Иван");

        Package packageItem =
                new Package(1, "Документы", 2.5);

        Delivery delivery =
                service.createDelivery(
                        1,
                        packageItem,
                        customer
                );

        service.assignCourier(
                delivery,
                courier
        );

        assertEquals(
                DeliveryStatus.ASSIGNED,
                delivery.getStatus()
        );

        service.changeStatus(
                delivery,
                DeliveryStatus.PICKED_UP
        );

        assertEquals(
                DeliveryStatus.PICKED_UP,
                delivery.getStatus()
        );

        service.changeStatus(
                delivery,
                DeliveryStatus.IN_TRANSIT
        );

        assertEquals(
                DeliveryStatus.IN_TRANSIT,
                delivery.getStatus()
        );

        service.changeStatus(
                delivery,
                DeliveryStatus.DELIVERED
        );

        assertEquals(
                DeliveryStatus.DELIVERED,
                delivery.getStatus()
        );
    }


    @Test
    public void testInvalidStatusChange() {

        DeliveryRepository repository =
                new InMemoryDeliveryRepository();

        DeliveryService service =
                new DeliveryService(repository);

        Customer customer =
                new Customer(1, "Анна", "+375291234567");

        Package packageItem =
                new Package(1, "Документы", 2.5);

        Delivery delivery =
                service.createDelivery(
                        1,
                        packageItem,
                        customer
                );

        assertThrows(
                IllegalStateException.class,
                () -> service.changeStatus(
                        delivery,
                        DeliveryStatus.DELIVERED
                )
        );
    }

    
        @Test
        public void testCustomerWithEmptyName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Customer(1, "", "123456789")
        );
        }


    @Test
    public void testCancelCreatedDelivery() {
        DeliveryRepository repository =
                new InMemoryDeliveryRepository();
        DeliveryService service = new DeliveryService(repository);
        Customer customer = new Customer(2, "Мария", "+375291112233");
        Package packageItem = new Package(2, "Книга", 1.0);

        Delivery delivery =
                service.createDelivery(2, packageItem, customer);

        service.cancelDelivery(delivery);

        assertEquals(DeliveryStatus.CANCELLED, delivery.getStatus());
    }

    @Test
    public void testCannotCancelDeliveryAfterPickup() {
        DeliveryRepository repository =
                new InMemoryDeliveryRepository();
        DeliveryService service = new DeliveryService(repository);
        Customer customer = new Customer(3, "Иван", "+375292223344");
        Courier courier = new Courier(3, "Пётр");
        Package packageItem = new Package(3, "Посылка", 3.0);

        Delivery delivery =
                service.createDelivery(3, packageItem, customer);
        service.assignCourier(delivery, courier);
        service.changeStatus(delivery, DeliveryStatus.PICKED_UP);

        assertThrows(
                IllegalStateException.class,
                () -> service.cancelDelivery(delivery)
        );
    }

    
    @Test
    public void testCannotDeliverBeforeTransit() {
        DeliveryRepository repository =
                new InMemoryDeliveryRepository();

        DeliveryService service =
                new DeliveryService(repository);

        Customer customer =
                new Customer(4, "Анна", "+375291234567");

        Courier courier =
                new Courier(4, "Иван");

        Package packageItem =
                new Package(4, "Документы", 2.5);

        Delivery delivery =
                service.createDelivery(
                        4,
                        packageItem,
                        customer
                );

        service.assignCourier(delivery, courier);

        assertThrows(
                IllegalStateException.class,
                () -> service.changeStatus(
                        delivery,
                        DeliveryStatus.DELIVERED
                )
        );

        assertEquals(
                DeliveryStatus.ASSIGNED,
                delivery.getStatus()
        );
    }
    
//     @Test
//     public void testCannotAssignNullCourier() {
//         DeliveryRepository repository =
//                 new InMemoryDeliveryRepository();

//         DeliveryService service =
//                 new DeliveryService(repository);

//         Customer customer =
//                 new Customer(5, "Мария", "+375291112233");

//         Package packageItem =
//                 new Package(5, "Документы", 1.0);

//         Delivery delivery =
//                 service.createDelivery(
//                         5,
//                         packageItem,
//                         customer
//                 );

//         assertThrows(
//                 IllegalArgumentException.class,
//                 () -> service.assignCourier(delivery, null)
//         );

//         assertEquals(
//                 DeliveryStatus.CREATED,
//                 delivery.getStatus()
//         );
    
// }
}