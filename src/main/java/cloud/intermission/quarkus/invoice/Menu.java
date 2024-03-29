package cloud.intermission.quarkus.invoice;

import java.util.List;

record Menu(
        Company company,
        List<MenuSection> sections
) {
    record MenuSection(
            String title,
            List<MenuItem> items
    ) { }

    record MenuItem(
            String title,
            String description,
            String price
    ) {}
}