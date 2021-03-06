package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.IDocumentTypeDao;
import ru.sgnhp.domain.DocumentTypeBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class DocumentTypeDaoImpl extends GenericDaoHibernate<DocumentTypeBean, Long> implements IDocumentTypeDao {

    public DocumentTypeDaoImpl() {
        super(DocumentTypeBean.class);
    }
}
