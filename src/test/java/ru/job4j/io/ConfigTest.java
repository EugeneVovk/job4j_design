package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username"), is("postgres"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithExc() {
        String path = "./data/pair_with_comment.txt";
        Config config = new Config(path);
        config.value("user");
    }

    @Test
    public void whenPairWitComment() {
        String path = "./data/pair_with_comment.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username"), is("postgres"));
    }
}