package jym.manager.commons.core;

import jym.manager.commons.core.Config;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConfigTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "App title : JYM Task Manager\n" +
                "Current log level : INFO\n" +
                "Preference file Location : preferences.json\n" +
                "Local data file location : data/taskmanager.xml\n" +
                "TaskManager name : MyTaskManager";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsMethod(){
        Config defaultConfig = new Config();
        assertFalse(defaultConfig.equals(null));
        assertTrue(defaultConfig.equals(defaultConfig));
    }


}