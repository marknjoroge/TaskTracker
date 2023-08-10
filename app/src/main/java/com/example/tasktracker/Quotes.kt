package com.example.tasktracker


class Quotes {
    val motivationalQuotes = mapOf(
        "The future depends on what you do today." to "Unknown",
        "Success is not final, failure is not fatal: It is the courage to continue that counts." to "Winston Churchill",
        "Don't watch the clock; do what it does. Keep going." to "Sam Levenson",
        "The only way to achieve the impossible is to believe it is possible." to "Charles Kingsleigh (Alice in Wonderland)",
        "Your time is limited, don't waste it living someone else's life." to "Steve Jobs",
        "Dreams don't work unless you do." to "John C. Maxwell",
        "The only person you should try to be better than is the person you were yesterday." to "A wise man",
        "Hard work beats talent when talent doesn't work hard." to "Tim Notke",
        "Success is walking from failure to failure with no loss of enthusiasm." to "Winston Churchill",
        "The journey of a thousand miles begins with one step." to "Lao Tzu",
        "Believe you can and you're halfway there." to "Theodore Roosevelt",
        "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart." to "Roy T. Bennett",
        "It always seems impossible until it's done." to "Nelson Mandela",
        "The only limit to our realization of tomorrow will be our doubts of today." to "Franklin D. Roosevelt",
        "Set your goals high, and don't stop till you get there." to "Bo Jackson"
    )

    var quotes = ArrayList<String>()
    var authors = ArrayList<String>()

    init {
        populateQuotes()
    }

    fun populateQuotes() {
        quotes.add("Success is no accident. It is hard work, perseverance, learning, studying, sacrifice, and most of all, love of what you are doing or learning to do.");
        authors.add("Pele");

        quotes.add("The future depends on what you do today.");
        authors.add("Mahatma Gandhi");

        quotes.add("The only way to achieve the impossible is to believe it is possible.");
        authors.add("Charles Kingsleigh");

        quotes.add("Don't watch the clock; do what it does. Keep going.");
        authors.add("Sam Levenson");

        quotes.add("The only person you should try to be better than is the person you were yesterday.");
        authors.add("Anonymous");

        quotes.add("It always seems impossible until it is done.");
        authors.add("Nelson Mandela");

        quotes.add("You are never too old to set another goal or to dream a new dream.");
        authors.add("C.S. Lewis");

        quotes.add("The best way to predict the future is to create it.");
        authors.add("Peter Drucker");

        quotes.add("Believe you can and you're halfway there.");
        authors.add("Theodore Roosevelt");

        quotes.add("Success is walking from failure to failure with no loss of enthusiasm.");
        authors.add("Winston Churchill");

        quotes.add("The only limit to our realization of tomorrow will be our doubts of today.");
        authors.add("Franklin D. Roosevelt");

        quotes.add("You don't have to be great to start, but you have to start to be great.");
        authors.add("Zig Ziglar");

        quotes.add("Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work.");
        authors.add("Steve Jobs");

        quotes.add("The future belongs to those who believe in the beauty of their dreams.");
        authors.add("Eleanor Roosevelt");

        quotes.add("The road to success and the road to failure are almost exactly the same.");
        authors.add("Colin R. Davis");

        quotes.add("Success is not final, failure is not fatal: It is the courage to continue that counts.");
        authors.add("Winston Churchill");

        quotes.add("In the middle of every difficulty lies opportunity.");
        authors.add("Albert Einstein");

        quotes.add("The harder the conflict, the greater the triumph.");
        authors.add("George Washington");

        quotes.add("You miss 100% of the shots you don't take.");
        authors.add("Wayne Gretzky");

        quotes.add("Dream big and dare to fail.");
        authors.add("Norman Vaughan");
    }
}