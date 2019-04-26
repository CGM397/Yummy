package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.CommodityInfoDao;
import edu.nju.Yummy.model.CommodityInfo;
import edu.nju.Yummy.model.DiscountInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CommodityInfoDaoImpl implements CommodityInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean saveCommodityInfo(CommodityInfo commodityInfo) {
        return baseDao.save(commodityInfo);
    }

    @Override
    public boolean updateCommodityInfo(CommodityInfo commodityInfo) {
        return baseDao.update(commodityInfo);
    }

    @Override
    public boolean updateDiscountInfo(DiscountInfo discountInfo) {
        return baseDao.update(discountInfo);
    }

    @Override
    public boolean saveDiscountInfo(DiscountInfo discountInfo) {
        return baseDao.save(discountInfo);
    }

    @Override
    public ArrayList<CommodityInfo> showCommodity(String restaurantId) {
        ArrayList<CommodityInfo> res = new ArrayList<>();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from CommodityInfo where restaurantId = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1,restaurantId);
            if(query.list().size() > 0) {
                List<CommodityInfo> store = query.list();
                for(CommodityInfo one : store){
                    if(isOnSale(one.getBeginDate(), one.getEndDate()))
                        res.add(one);
                }
            }
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<CommodityInfo> showCommodityToCome(String restaurantId) {
        ArrayList<CommodityInfo> res = new ArrayList<>();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from CommodityInfo where restaurantId = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1,restaurantId);
            if(query.list().size() > 0) {
                List<CommodityInfo> store = query.list();
                for(CommodityInfo one : store){
                    if(isToCome(one.getBeginDate()))
                        res.add(one);
                }
            }
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private boolean isOnSale(Date beginDate, Date endDate){
        Date now = new Date();
        return now.after(beginDate) && now.before(endDate);
    }

    private boolean isToCome(Date beginDate){
        Date now = new Date();
        return now.before(beginDate);
    }
}
