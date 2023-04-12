/**
 * Provides the starter code for the <strong>cs1302-omega</strong> project.
 */
module cs1302.omega {
    requires transitive java.logging;
    requires transitive java.net.http;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.web;
    requires transitive com.google.gson;
    exports cs1302.api;
    exports cs1302.omega;
} // module
