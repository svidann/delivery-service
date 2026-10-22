import model.Courier;
import model.Customer;
import model.Delivery;
import model.DeliveryStatus;
import model.Package;
import repository.DeliveryRepository;
import repository.InMemoryDeliveryRepository;
import service.DeliveryService;

public class Application {

    public static void main(String[] args) {

        DeliveryRepository repository =
                new InMemoryDeliveryRepository();

        DeliveryService service =
                new DeliveryService(repository);

        Customer customer =
                new Customer(
                        1,
                        "Анна",
                        "+375291234567"
                );

        Courier courier =
                new Courier(
                        1,
                        "Иван"
                );

        Package packageItem =
                new Package(
                        1,
                        "Документы",
                        0.5
                );

        Delivery delivery =
                service.createDelivery(
                        1,
                        packageItem,
                        customer
                );

        System.out.println(
                "Статус после создания: "
                        + delivery.getStatus()
        );

        service.assignCourier(
                delivery,
                courier
        );

        System.out.println(
                "Статус после назначения курьера: "
                        + delivery.getStatus()
        );

        service.changeStatus(
                delivery,
                DeliveryStatus.PICKED_UP
        );

        System.out.println(
                "Статус после получения посылки: "
                        + delivery.getStatus()
        );

        service.changeStatus(
                delivery,
                DeliveryStatus.IN_TRANSIT
        );

        System.out.println(
                "Статус во время доставки: "
                        + delivery.getStatus()
        );

        service.changeStatus(
                delivery,
                DeliveryStatus.DELIVERED
        );

        System.out.println(
                "Статус после доставки: "
                        + delivery.getStatus()
        );

        System.out.println();
        System.out.println(
                "Проверка недопустимого перехода:"
        );

        try {

            service.changeStatus(
                    delivery,
                    DeliveryStatus.CREATED
            );

        } catch (IllegalStateException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }
}