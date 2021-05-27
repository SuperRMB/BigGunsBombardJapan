package com.zh.biggunsbombardjapan.person;

import android.graphics.Point;

import java.util.List;

/**
 * 规则
 */
public class RuleTools {

    private int spacing;
    private List<BaseRole> mCannons;
    private List<BaseRole> mSoldiers;

    public RuleTools(int spacing) {
        this.spacing = spacing;
    }

    public void setRole(List<BaseRole> cannons, List<BaseRole> soldiers) {
        mCannons = cannons;
        mSoldiers = soldiers;
    }

    /**
     * 是否可以移动
     *
     * @param start 起始位置
     * @param end   终点
     * @param role  角色
     * @return
     */
    public boolean isCanRemove(Point start, Point end, int role) {
        if (start.x == end.x) {
            if (role == 1) {
                Point[] temp;
                if (end.y - start.y > 0) {
                    int count = (end.y - start.y) / spacing;
                    temp = new Point[count];
                    for (int i = 0; i < count; i++) {
                        int tt = start.y + spacing * (i + 1);
                        Point t = new Point(start.x, tt);
                        temp[i] = t;
                    }
                } else {
                    int count = (start.y - end.y) / spacing;
                    temp = new Point[count];
                    for (int i = 0; i < count; i++) {
                        int tt = start.y - spacing * (i + 1);
                        Point t = new Point(start.x, tt);
                        temp[i] = t;
                    }
                }
                return isCannonDropChess(temp);
            } else if (role == 2) {
                return abs(start.y - end.y) == spacing &&
                        isCanSoldierDropChess(end);
            }
        } else if (start.y == end.y) {
            if (role == 1) {
                Point[] temp;
                if (end.x - start.x > 0) {
                    int count = (end.x - start.x) / spacing;
                    temp = new Point[count];
                    for (int i = 0; i < count; i++) {
                        int tt = start.x + spacing * (i + 1);
                        Point t = new Point(tt, start.y);
                        temp[i] = t;
                    }
                } else {
                    int count = (start.x - end.x) / spacing;
                    temp = new Point[count];
                    for (int i = 0; i < count; i++) {
                        int tt = start.x - spacing * (i + 1);
                        Point t = new Point(tt, start.y);
                        temp[i] = t;
                    }
                }
                return isCannonDropChess(temp);
            } else if (role == 2) {
                return abs(start.x - end.x) == spacing &&
                        isCanSoldierDropChess(end);
            }
        }
        return false;
    }

    /**
     * 检测大炮是否可以落棋
     *
     * @param points
     * @return
     */
    private boolean isCannonDropChess(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            for (int j = 0; j < mCannons.size(); j++) {
                BaseRole baseRole = mCannons.get(j);
                if (point.equals(baseRole.getPoint())) {
                    return false;
                }
            }

            for (int j = 0; j < mSoldiers.size(); j++) {
                BaseRole baseRole = mSoldiers.get(j);
                if (point.equals(baseRole.getPoint())) {
                    if (points.length == 2 && i + 1 == 2) {
                        mSoldiers.remove(baseRole);
                        return true;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检测小兵是否可以落棋
     *
     * @param point
     * @return
     */
    private boolean isCanSoldierDropChess(Point point) {
        for (int i = 0; i < mCannons.size(); i++) {
            BaseRole baseRole = mCannons.get(i);
            if (point.equals(baseRole.getPoint())) {
                return false;
            }
        }
        return true;
    }

    private int abs(int x) {
        return Math.abs(x);
    }

    private void obstacleDetection(Point start, Point end, int role) {

    }
}
