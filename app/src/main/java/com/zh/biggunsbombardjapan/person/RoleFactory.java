package com.zh.biggunsbombardjapan.person;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class RoleFactory {

    private List<BaseRole> mSoldierList = new ArrayList<>();
    private List<BaseRole> mCannonList = new ArrayList<>();

    public void initRole(List<Point> points) {
        mCannonList.clear();
        mSoldierList.clear();

        for (int i = 0; i < 2; i++) {
            Point point = points.get(i + 1);
            Cannon cannon = new Cannon(point, 40);
            mCannonList.add(cannon);
        }

        for (int i = 0; i < 8; i++) {
            Point point = points.get(i + 8);
            Soldier cannon = new Soldier(point, 30);
            mSoldierList.add(cannon);
        }
    }

    public List<BaseRole> getSoldierList() {
        return mSoldierList;
    }


    public List<BaseRole> getCannonList() {
        return mCannonList;
    }


    public BaseRole getRole(Point point, int mCurrRoleRun) {
        if (mCurrRoleRun == 1) {
            for (int i = 0; i < mCannonList.size(); i++) {
                BaseRole baseRole = mCannonList.get(i);
                if (point.equals(baseRole.getPoint())) {
                    return baseRole;
                }
            }
        } else if (mCurrRoleRun == 2) {
            for (int i = 0; i < mSoldierList.size(); i++) {
                BaseRole baseRole = mSoldierList.get(i);
                if (point.equals(baseRole.getPoint())) {
                    return baseRole;
                }
            }
        }
        return null;
    }


    public void setRole(Point point, BaseRole selectRole, int mCurrRoleRun) {
        if (mCurrRoleRun == 1) {
            for (int i = 0; i < mCannonList.size(); i++) {
                BaseRole baseRole = mCannonList.get(i);
                if (baseRole == selectRole) {
                    baseRole.setPoint(point);
                }
            }
        } else if (mCurrRoleRun == 2) {
            for (int i = 0; i < mSoldierList.size(); i++) {
                BaseRole baseRole = mSoldierList.get(i);
                if (baseRole == selectRole) {
                    baseRole.setPoint(point);
                }
            }
        }
    }

}
