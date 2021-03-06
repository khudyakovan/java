package ru.sgnhp.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sgnhp.dao.ITaskDao;
import ru.sgnhp.domain.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class TaskDaoImpl extends GenericDaoHibernate<TaskBean, Long> implements ITaskDao {

    public TaskDaoImpl() {
        super(TaskBean.class);
    }

    @Override
    public List<TaskBean> getTaskByInternalNumber(int number) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("internalNumber", number);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByInternalNumber", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<TaskBean> getTaskByExternalNumber(String number) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("externalNumber", number);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByExternalNumber", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<TaskBean> getTaskByIncomingNumber(int number) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("incomingNumber", number);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByIncomingNumber", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<TaskBean> getTasksByExternalAssignee(String externalAssignee) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("externalAssignee", externalAssignee);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByExternalAssignee", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<TaskBean> getTasksByDescription(String description) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("description", description);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByDescription", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public int getNewInternalNumber() {
//        Calendar today = Calendar.getInstance();
//        int year = today.get(Calendar.YEAR);
//        List list = getSession().createQuery(String.format("SELECT Max(t.internalNumber) " +
//                "FROM TaskBean t where t.startDate BETWEEN '%1$s-01-01' AND '%1$s-12-31'", +
//                year)).list();
        List list = getSession().createQuery("SELECT Max(t.internalNumber) FROM TaskBean t").list();
        if (list.get(0) instanceof Integer) {
            return (Integer) list.get(0);
        }
        return -1;
    }

    @Override
    public int getNewIncomingNumber() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        List list = getSession().createQuery(String.format("SELECT Max(t.incomingNumber) "
                + "FROM TaskBean t where t.startDate BETWEEN '%1$s-01-01' AND '%1$s-12-31'",
                year)).list();
        if (list.get(0) instanceof Integer) {
            return (Integer) list.get(0);
        }
        return -1;
    }

    @Override
    public List<TaskBean> getTaskByExternalCompany(String externalCompany) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("externalCompany", externalCompany);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByExternalCompany", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<TaskBean> getAllIncomingMailByYear(Integer currentYear) throws ParseException {
        Map<String, Object> value = new HashMap<String, Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate = simpleDateFormat.parse("01.01." + currentYear.toString());
        Date finishDate = simpleDateFormat.parse("31.12." + currentYear.toString());
        value.put("startDate", startDate);
        value.put("finishDate", finishDate);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findAllIncomingMailByYear", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<TaskBean> getTaskByPrimaveraUid(String primaveraUid) {
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("primaveraUid", primaveraUid);
        List<TaskBean> list = this.findByNamedQuery("TaskBean.findByPrimaveraUid", value);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<String> getDistinctPrimaveraIDS() {
        List list = getSession().createQuery("SELECT distinct t.primaveraUid "
                + "FROM TaskBean t WHERE t.primaveraUid <> ''").list();
        return list;
    }

    @Override
    public List<String> getDistinctExternalCompanies(String query) {
        List list = null;
        if (!query.equals("")) {
            list = getSession().createQuery("SELECT distinct t.externalCompany "
                    + "FROM TaskBean t WHERE t.externalCompany <> '' AND t.externalCompany like "
                    + "'%" + query + "%' ORDER BY t.externalCompany").list();
        }
        return list;
    }

    @Override
    public List<String> getExternalAssignees(String query) {
        List list = null;
        if (!query.equals("")) {
            list = getSession().createQuery("SELECT distinct t.externalAssignee "
                    + "FROM TaskBean t WHERE t.externalAssignee <> '' AND t.externalAssignee like "
                    + "'%" + query + "%' ORDER BY t.externalAssignee").list();
        }
        return list;
    }

    @Override
    public TaskBean saveEx(TaskBean taskBean) {
        Session sess = this.getSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();

            taskBean = this.save(taskBean);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e; // or display error message
        } finally {
            sess.close();
        }
        return taskBean;
    }
}
