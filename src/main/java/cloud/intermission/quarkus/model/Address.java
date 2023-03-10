package cloud.intermission.quarkus.model;

record Address (
    String line1,
    String line2,
    String city,
    String state,
    String country,
    String zipCode
) {}
