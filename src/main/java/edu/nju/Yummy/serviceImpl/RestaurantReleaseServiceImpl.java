package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.CommodityInfoDao;
import edu.nju.Yummy.model.CommodityInfo;
import edu.nju.Yummy.model.DiscountInfo;
import edu.nju.Yummy.service.RestaurantReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RestaurantReleaseServiceImpl implements RestaurantReleaseService {

    @Autowired
    private CommodityInfoDao commodityInfoDao;

    @Override
    public boolean releaseProduct(CommodityInfo commodityInfo) {
        return commodityInfoDao.saveCommodityInfo(commodityInfo);
    }

    @Override
    public boolean releaseDiscount(DiscountInfo discountInfo) {
        return commodityInfoDao.saveDiscountInfo(discountInfo);
    }

    @Override
    public ArrayList<CommodityInfo> showCommodity(String restaurantId) {
        return commodityInfoDao.showCommodity(restaurantId);
    }

    @Override
    public ArrayList<CommodityInfo> showCommodityToCome(String restaurantId) {
        return commodityInfoDao.showCommodityToCome(restaurantId);
    }
}
