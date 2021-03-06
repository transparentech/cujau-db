package org.cujau.db2.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.cujau.db2.AbstractDBUtility;
import org.cujau.db2.AbstractDBUtilityImpl;
import org.cujau.db2.DAOInitializationException;
import org.cujau.db2.H2DBUtilityHelpers;
import org.cujau.db2.Migration;
import org.cujau.db2.MigrationInitializationException;

public class DBVersionDAOTest {

    private AbstractDBUtility dbutil;

    @Before
    public void before()
            throws IOException {
        dbutil = new AbstractDBUtilityImpl() {

            @Override
            public List<DAO> instantiateDAOs(Properties props)
                    throws DAOInitializationException {
                return new ArrayList<>();
            }

            @Override
            protected int getPostSchemaCreationDBVersion() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public List<Migration> instantiateMigrations(Properties props)
                    throws MigrationInitializationException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public int getLowestMigrationNumber() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getHighestMigrationNumber() {
                // TODO Auto-generated method stub
                return 0;
            }

        };

        H2DBUtilityHelpers.initAndCreateInMemoryDB(dbutil);
    }

    @After
    public void after() {
        dbutil.dropDBSchema();
    }

    @Test
    public void testDefaultVersion() {
        assertEquals(0, dbutil.getDBVersionDAO().getVersion());
    }

    @Test
    public void testUpdateVersion() {
        assertEquals(0, dbutil.getDBVersionDAO().getVersion());
        dbutil.getDBVersionDAO().setVersion(5);
        assertEquals(5, dbutil.getDBVersionDAO().getVersion());
    }
}
