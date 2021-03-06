/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.dao.impl;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import ru.sgnhp.dao.IUserDao;

/**
 *
 * @author 48han
 */
public class UserDaoImplTest extends AbstractTransactionalDataSourceSpringContextTests {

    private IUserDao userDao;

    public UserDaoImplTest() {
    }

    @Test
    public void testGetAll() {
        assertEquals(userDao.getAll().size(), 99);
    }
    
    @Test
    public void testGetByLogin() {
        assertNotNull(userDao.getByLogin("77han"));
    }

    @Test
    public void testGetByEmail() {
        assertNotNull(userDao.getByEmail("77han@salavatmed.ru"));
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-context.xml"};
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

}