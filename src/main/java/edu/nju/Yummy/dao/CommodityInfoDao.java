package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.CommodityInfo;
import edu.nju.Yummy.model.DiscountInfo;

import java.util.ArrayList;

public interface CommodityInfoDao {

    boolean saveCommodityInfo(CommodityInfo commodityInfo);

    boolean saveDiscountInfo(DiscountInfo discountInfo);

    ArrayList<CommodityInfo> showCommodity(String restaurantId);
}