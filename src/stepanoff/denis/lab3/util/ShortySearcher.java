package stepanoff.denis.lab3.util;

import stepanoff.denis.lab3.shorties.Shorty;

public class ShortySearcher {

    private final ShortyCollection collection;

    public ShortySearcher(ShortyCollection collection) {
        this.collection = collection;
    }

    public Shorty getShorty(String name) throws ShortyNotFoundException {
        for (Shorty s : this.collection.toArray()) {
            if (s.getName().equals(name))
                return s;
        }

        throw new ShortyNotFoundException(name); // here is no such shorty in given collection, if we passed here
    }
}
