package org.meerkatdev.lib;

import java.util.Random;

public class JokeLibrary {

    private String[] jokes;
    private Random random;

    public JokeLibrary() {
        jokes = new String[4];
        jokes[0] = "Q: How do you weigh a millennial?\n A: In Instagrams.";
        jokes[1] = "Q: What happened to the guy who sued over his missing luggage?\n A: He lost his case.";
        // from reddit :)
        jokes[2] = "A SQL query walks into a bar and sees two tables. He walks up to them and says 'Can I join you?'";
        jokes[3] = "I can't think of any SQL jokes like you guys, I feel so left outer";
        random = new Random();
    }

    public String[] getJokes() {
        return jokes;
    }

    public String getRandomElement() {
        return jokes[random.nextInt(jokes.length)];
    }
}
