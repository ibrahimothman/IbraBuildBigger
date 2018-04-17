package com.example;

import java.util.Random;

public class MyJokes {

    private String[]jokes;

    public MyJokes() {
        jokes = new String[]{"Why do Java developers wear glasses? Because they can't C#",
                "Q: How many prolog programmers does it take to change a lightbulb? A: Yes.",
                "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\""};
    }

    public String tellMeJoke(){
        Random random = new Random();
        return jokes[random.nextInt(jokes.length)];
    }
}
