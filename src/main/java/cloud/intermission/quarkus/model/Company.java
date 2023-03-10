package cloud.intermission.quarkus.model;

record Company(
    String name,
    String logoFile,
    Address address,
    String email,
    String website,
    String phone
) { }
