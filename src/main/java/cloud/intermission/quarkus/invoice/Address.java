package cloud.intermission.quarkus.invoice;

record Address (
    String line1,
    String line2,
    String city,
    String state,
    String country,
    String zipCode
) {}
