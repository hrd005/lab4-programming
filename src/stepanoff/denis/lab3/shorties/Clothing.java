package stepanoff.denis.lab3.shorties;

import java.util.Arrays;

public class Clothing {

    private String title;
    private String material = "";
    private int numberOfPockets = 0;
    private String[] parts;

    public String getTitle() {
        return this.title;
    }

    public int getNumberOfPockets() {
        return this.numberOfPockets;
    }

    @Override
    public String toString() {
        String str = this.title;
        if (!this.material.isEmpty())
            str += ", made from " + this.material;
        if (this.numberOfPockets != 0)
            str += ", have " + this.numberOfPockets + " pockets";
        if (this.parts != null)
            str += ", consist of " + Arrays.toString(this.parts);

        return str;
    }

    public static class Builder {
        private final Clothing c = new Clothing();

        public Builder title(String title) {
            c.title = title;
            return this;
        }

        public Builder material(String material) {
            c.material = material;
            return this;
        }

        public Builder pockets(int number) {
            c.numberOfPockets = number;
            return this;
        }

        public Builder parts(String... partsTitle) {
            c.parts = partsTitle;
            return this;
        }

        public Clothing build() {
            return this.c;
        }
    }
}
