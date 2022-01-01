package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    RoleStore store = new RoleStore();

    @Test
    public void whenAddAndFindThenNameIsTom() {
        store.add(new Role("1", "Tom"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Tom"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        store.add(new Role("1", "Tom"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindNameIsTom() {
        store.add(new Role("1", "Tom"));
        store.add(new Role("1", "Jerry"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Tom"));
    }

    @Test
    public void whenReplaceThenNameIsJerry() {
        store.add(new Role("1", "Tom"));
        store.replace("1", new Role("1", "Jerry"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Jerry"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeName() {
        store.add(new Role("1", "Tom"));
        store.replace("10", new Role("10", "Jerry"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Tom"));
    }

    @Test
    public void whenDeleteUserThenRoleIsNull() {
        store.add(new Role("1", "Tom"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenNameIsTom() {
        store.add(new Role("1", "Tom"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName(), is("Tom"));
    }
}
