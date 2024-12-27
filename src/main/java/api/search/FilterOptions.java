package api.search;

import lombok.Getter;

public class FilterOptions {
    @Getter
    public enum License {
        ALL("All"),
        COMMERCIAL("Commercial"),
        PERSONAL("Personal");

        private final String label;

        License(String label) {
            this.label = label;
        }
    }

    @Getter
    public enum Color {
        PURPLE("Purple"),
        BLUE("Blue"),
        RED("Red"),
        PINK("Pink"),
        GREEN("Green"),
        ORANGE("Orange"),
        YELLOW("Yellow"),
        BROWN("Brown"),
        BLACK("Black"),
        GRAY("Gray"),
        WHITE("White");

        private final String label;

        Color(String label) {
            this.label = label;
        }
    }
}
