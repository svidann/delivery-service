package service;

import model.Courier;
import model.Customer;
import model.Delivery;
import model.DeliveryStatus;
import model.Package;
import repository.DeliveryRepository;

public class DeliveryService {

    private final DeliveryRepository repository;

    public DeliveryService(DeliveryRepository repository) {
        this.repository = repository;
    }

    public Delivery createDelivery(
            int deliveryId,
            Package packageItem,
            Customer customer) {

        Delivery delivery =
                new Delivery(
                        deliveryId,
                        packageItem,
                        customer
                );

        repository.save(delivery);

        return delivery;
    }

    public void assignCourier(
            Delivery delivery,
            Courier courier) {

        if (delivery.getStatus()
                != DeliveryStatus.CREATED) {

            throw new IllegalStateException(
                    "Курьера можно назначить только для новой доставки"
            );
        }

        delivery.setCourier(courier);

        changeStatus(
                delivery,
                DeliveryStatus.ASSIGNED
        );
    }

    public void changeStatus(
            Delivery delivery,
            DeliveryStatus newStatus) {

        DeliveryStatus currentStatus =
                delivery.getStatus();

        if (!isValidTransition(
                currentStatus,
                newStatus)) {

            throw new IllegalStateException(
                    "Недопустимый переход статуса: "
                            + currentStatus
                            + " -> "
                            + newStatus
            );
        }

        delivery.setStatus(newStatus);
    }

    /**
     * Отменяет доставку до передачи посылки курьеру на доставку.
     * Отмена разрешена только для статусов CREATED и ASSIGNED.
     */
    public void cancelDelivery(Delivery delivery) {
        DeliveryStatus currentStatus = delivery.getStatus();

        if (currentStatus != DeliveryStatus.CREATED
                && currentStatus != DeliveryStatus.ASSIGNED) {
            throw new IllegalStateException(
                    "Отменить можно только новую доставку "
                            + "или доставку с назначенным курьером"
            );
        }

        delivery.setStatus(DeliveryStatus.CANCELLED);
    }

    private boolean isValidTransition(
            DeliveryStatus currentStatus,
            DeliveryStatus newStatus) {

        if (currentStatus == DeliveryStatus.CREATED) {
            return newStatus == DeliveryStatus.ASSIGNED;
        }

        if (currentStatus == DeliveryStatus.ASSIGNED) {
            return newStatus == DeliveryStatus.PICKED_UP;
        }

        if (currentStatus == DeliveryStatus.PICKED_UP) {
            return newStatus == DeliveryStatus.IN_TRANSIT;
        }

        if (currentStatus == DeliveryStatus.IN_TRANSIT) {
            return newStatus == DeliveryStatus.DELIVERED;
        }

        return false;
    }
}