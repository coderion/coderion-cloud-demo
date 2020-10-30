package pl.coderion.configuration;

public interface ConstantValues {

    String APPLICATION_NAME = "coderion-cloud-demo";
    String DEFAULT_TIMEZONE = "Europe/Warsaw";
    String DEFAULT_LANGUAGE = "pl";

    // HTTP
    String HEADER_ERROR_CODE = "X-UptimeCounter-Error-Code";        // nazwa headera z kodem błędu
    String HEADER_ERROR_MESSAGE = "X-UptimeCounter-Error-Message";  // nazwa headera z komunikatem błędu (jeśli nie ma kodu)
}