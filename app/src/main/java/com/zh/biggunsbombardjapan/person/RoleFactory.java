package com.zh.biggunsbombardjapan.person;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class RoleFactory {

    private List<BaseRole> mSoldierList = new ArrayList<>();
    private List<BaseRole> mCannonList = new ArrayList<>();

    public void initRole(List<Point> points){
        mCannonList.clear();
        mSoldierList.clear();

        for (int i = 0; i < 2; i++) {
            Point point = points.get(i+1);
            Cannon cannon = new Cannon(point,40);
            mCannonList.add(cannon);
        }

        for (int i = 0; i < 8; i++) {
            Point point = points.get(i+8);
            Soldier cannon = new Soldier(point,30);
            mSoldierList.add(cannon);
        }
    }

    public List<BaseRole> getSoldierList() {
        return mSoldierList;
    }



    public List<BaseRole> getCannonList() {
        return mCannonList;
    }


}
