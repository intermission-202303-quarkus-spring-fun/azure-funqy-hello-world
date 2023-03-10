package cloud.intermission.quarkus.invoice;

record Company(
    String name,
    String logoFile,
    Address address,
    String email,
    String website,
    String phone
) { }
