module com.example.projetocrud1javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.projetocrud1javafx to javafx.fxml;
    exports com.example.projetocrud1javafx;
}