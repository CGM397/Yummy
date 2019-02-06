package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.RestaurantInfoDao;
import edu.nju.Yummy.model.Restaurant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class RestaurantInfoDaoImpl implements RestaurantInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public ArrayList<Restaurant> showAllRestaurants() {
        ArrayList<Restaurant> res = new ArrayList<>();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from Restaurant";
            Query query = session.createQuery(hql);
            if(query.list().size() > 0)
                res = (ArrayList<Restaurant>) query.list();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean saveRestaurantInfo(Restaurant restaurant) {
        return baseDao.save(restaurant);
    }

    @Override
    public Restaurant findRestaurantById(String restaurantId) {
        Restaurant res = new Restaurant();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            res = session.get(Restaurant.class, restaurantId);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
