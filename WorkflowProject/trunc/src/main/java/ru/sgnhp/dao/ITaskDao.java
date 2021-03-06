package ru.sgnhp.dao;

import java.text.ParseException;
import java.util.List;
import ru.sgnhp.domain.TaskBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public interface ITaskDao extends IGenericDao<TaskBean, Long> {

    TaskBean saveEx(TaskBean taskBean);

    List<TaskBean> getAllIncomingMailByYear(Integer currentYear) throws ParseException;

    List<TaskBean> getTaskByInternalNumber(int number);

    List<TaskBean> getTaskByExternalNumber(String number);

    List<TaskBean> getTaskByExternalCompany(String externalCompany);

    List<TaskBean> getTaskByIncomingNumber(int number);

    List<TaskBean> getTasksByExternalAssignee(String externalAssignee);

    List<TaskBean> getTaskByPrimaveraUid(String primaveraUid);

    int getNewInternalNumber();

    int getNewIncomingNumber();

    List<TaskBean> getTasksByDescription(String description);

    List<String> getDistinctPrimaveraIDS();

    List<String> getDistinctExternalCompanies(String query);

    List<String> getExternalAssignees(String query);
}
