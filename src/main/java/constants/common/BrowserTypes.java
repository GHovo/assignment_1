package constants.common;

import lombok.Getter;

@Getter
public enum BrowserTypes {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String type;

    BrowserTypes(String type){
        this.type = type;
    }

    public static BrowserTypes parse(String type) {
        for (BrowserTypes browserType : BrowserTypes.values()) {
            if (browserType.getType().equals(type)) {
                return browserType;
            }
        }
        throw new IllegalArgumentException("Does not exist " +  type + "type");
    }
}
