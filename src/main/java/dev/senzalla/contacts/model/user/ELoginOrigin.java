package dev.senzalla.contacts.model.user;

public enum ELoginOrigin {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    LOCAL("local");

    private final String ORIGIN;

    ELoginOrigin(String ORIGIN) {
        this.ORIGIN = ORIGIN;
    }

    public static ELoginOrigin getOrigin(String origin) {
        return switch (origin) {
            case "facebook" -> ELoginOrigin.FACEBOOK;
            case "google" -> ELoginOrigin.GOOGLE;
            case "local" -> ELoginOrigin.LOCAL;
            default -> null;
        };
    }
}
